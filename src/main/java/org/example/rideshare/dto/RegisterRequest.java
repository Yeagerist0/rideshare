package org.example.rideshare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RegisterRequest {
    @NotBlank
    @Size(min = 3)
    private String username;

    @NotBlank
    @Size(min = 4)
    private String password;

    @NotBlank
    private String role;
}
