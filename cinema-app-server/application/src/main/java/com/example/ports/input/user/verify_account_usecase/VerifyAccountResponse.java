package com.example.ports.input.user.verify_account_usecase;

import com.example.model.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyAccountResponse {
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
