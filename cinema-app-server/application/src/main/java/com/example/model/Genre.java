package com.example.model;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    private Long Id;

    @NotBlank
    private String type;

    public Genre(String type) {
        this.type = type;
    }
}
