package com.vttai.Identify.service.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.vttai.Identify.service.dto.request.AuthenticationRequest;
import com.vttai.Identify.service.dto.request.IntrospectRequest;
import com.vttai.Identify.service.dto.response.AuthenticationResponse;
import com.vttai.Identify.service.dto.response.IntrospectResponse;
import com.vttai.Identify.service.entity.User;
import com.vttai.Identify.service.exception.AppException;
import com.vttai.Identify.service.exception.ErrorCode;
import com.vttai.Identify.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;

    // injected from application properties: e.g. jwt.signerKey=your-secret-key
    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    /**
     * Validate and introspect a JWT token.
     */
    public IntrospectResponse introspect(IntrospectRequest request) {
        String token = request.getToken();
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);

            // verify signature
            MACVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
            boolean verified = signedJWT.verify(verifier);

            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            boolean notExpired = expiryTime != null && expiryTime.after(new Date());

            return IntrospectResponse.builder()
                    .valid(verified && notExpired)
                    .build();

        } catch (ParseException | JOSEException e) {
            log.error("Failed to introspect token", e);
            // You can wrap into your AppException or return valid=false depending on your design
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
    }

    /**
     * Authenticate user by username/password and return a signed token when successful.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) throws JOSEException {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var token =  generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    /**
     * Create a signed JWT for the given username.
     */
    private String generateToken(User user) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("vttai.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", buildScope(user))
                .build();

        SignedJWT signedJWT = new SignedJWT(header, claims);

        MACSigner signer = new MACSigner(SIGNER_KEY.getBytes());
        signedJWT.sign(signer);

        return signedJWT.serialize();

    }
    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner("");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> stringJoiner.add(role.getName()));
        }
        return stringJoiner.toString();
    }
}
