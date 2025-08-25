package com.vttai.Identify.service.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.vttai.Identify.service.validator.DobConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String firstName;
    String lastName;

    @DobConstraint(min = 2, message = "INVALID_DOB")
    LocalDate dob;

    List<String> roles;
}
