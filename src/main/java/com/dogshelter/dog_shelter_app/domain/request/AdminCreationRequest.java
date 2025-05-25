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
public class AdminCreationRequest {
    @NotBlank(message = "Username-ul este obligatoriu")
    @Size(min = 3, max = 50, message = "Username-ul trebuie să aibă între 3 și 50 de caractere")
    @Pattern(
            regexp = "^[a-zA-Z0-9_]+$",
            message = "Username-ul poate conține doar litere, cifre și underscore"
    )
    private String username;
    @NotBlank(message = "Parola este obligatorie")
    @Size(min = 8, max = 100, message = "Parola trebuie să aibă între 8 și 100 de caractere")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Parola trebuie să conțină: o literă mică, o literă mare, o cifră și un caracter special (@$!%*?&)"
    )
    private String password;
}
