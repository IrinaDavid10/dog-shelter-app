package com.dogshelter.dog_shelter_app.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateClientRequest {
    @NotBlank(message = "Prenumele este obligatoriu")
    private String firstName;
    @NotBlank(message = "Numele este obligatoriu")
    private String lastName;
    @NotBlank(message = "Numarul de telefon este obligatoriu")
    private String phoneNumber;
}
