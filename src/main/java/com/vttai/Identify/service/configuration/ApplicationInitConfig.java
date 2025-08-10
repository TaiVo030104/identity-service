package com.vttai.Identify.service.configuration;

import com.vttai.Identify.service.entity.User;
import com.vttai.Identify.service.entity.Role;
import com.vttai.Identify.service.repository.UserRepository;
import com.vttai.Identify.service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository){
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                // Create or get the ADMIN role
                Role adminRole = roleRepository.findById("ADMIN")
                    .orElseGet(() -> {
                        Role role = new Role("ADMIN", "Administrator role", null);
                        return roleRepository.save(role);
                    });
                
                Set<Role> roles = new HashSet<>();
                roles.add(adminRole);
                
                User user = new User();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("admin"));
                user.setRoles(roles);
                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
        };
    }
}
