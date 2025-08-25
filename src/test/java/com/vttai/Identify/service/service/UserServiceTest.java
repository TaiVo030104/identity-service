package com.vttai.Identify.service.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import com.vttai.Identify.service.dto.request.UserCreateRequest;
import com.vttai.Identify.service.dto.response.UserResponse;
import com.vttai.Identify.service.entity.User;
import com.vttai.Identify.service.exception.AppException;
import com.vttai.Identify.service.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/test.properties")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreateRequest request;
    private UserResponse userResponse;
    private User user;
    private LocalDate date;

    @BeforeEach
    void initData() {
        LocalDate dob = LocalDate.of(1990, 1, 1);
        request = UserCreateRequest.builder()
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .password("12345678")
                .dob(dob)
                .build();
        userResponse = UserResponse.builder()
                .id("of0600f538b3")
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(request);

        // THEN
        assertThat(response.getId()).isEqualTo("cf0600f538b");
        assertThat(response.getUsername()).isEqualTo("john");
    }

    @Test
    void createUser_userExisted_fail() throws Exception {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        // WHEN
        var exception = assertThrows(AppException.class, () -> userService.createUser(request));

        // THEN
        assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
    }
}
