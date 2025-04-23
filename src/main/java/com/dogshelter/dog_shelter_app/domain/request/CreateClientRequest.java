package com.dogshelter.dog_shelter_app.domain.request;

import lombok.Data;

@Data
public class CreateClientRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
