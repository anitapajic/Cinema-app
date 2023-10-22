package com.example.ports.input.user.login_usecase;

import com.example.model.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;

    private Long id;

    private String username;

    private String name;

    private LocalDate dateOfBirth;

    private Role role;

    private Boolean verified;

    private Boolean blocked;
}
