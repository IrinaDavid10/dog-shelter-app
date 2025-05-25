package com.dogshelter.dog_shelter_app.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginRequest {
    @NotBlank(message = "Username-ul este obligatoriu")
    private String username;
    @NotBlank(message = "Parola este obligatorie")
    private String password;
}
