package com.dogshelter.dog_shelter_app.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateDogRequest {
    @NotBlank(message = "Numele este obligatoriu")
    private String name;
    @NotBlank(message = "Rasa este obligatorie")
    private String breed;
}
