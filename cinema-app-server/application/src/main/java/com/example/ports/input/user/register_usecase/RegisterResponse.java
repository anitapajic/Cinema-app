package com.example.ports.input.user.register_usecase;

import com.example.model.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
    @NotBlank
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String name;

    private LocalDate dateOfBirth;
    @NotBlank
    private Role role;
    @NotBlank
    private Boolean verified;
    @NotBlank
    private Boolean blocked;
}
