package com.dogshelter.dog_shelter_app.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateDogRequest {
    @NotNull(message = "Name may not be null")
    private String name;
    @NotNull(message = "Breed may not be null")
    private String breed;
}
