package com.dogshelter.dog_shelter_app.business;

import com.dogshelter.dog_shelter_app.persistance.entity.ClientEntity;
import com.dogshelter.dog_shelter_app.persistance.entity.DogEntity;

public interface ClientService {
    public ClientEntity createClient(String firstName, String lastName, String phoneNumber);
}
