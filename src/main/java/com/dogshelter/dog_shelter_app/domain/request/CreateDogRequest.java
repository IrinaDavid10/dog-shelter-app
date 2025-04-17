package com.dogshelter.dog_shelter_app.domain.request;

import lombok.Data;

@Data
public class CreateDogRequest {
    private String name;
    private String breed;
}
