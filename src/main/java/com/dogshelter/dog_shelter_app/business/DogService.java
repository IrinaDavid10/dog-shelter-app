package com.dogshelter.dog_shelter_app.business;

import com.dogshelter.dog_shelter_app.persistance.entity.DogEntity;

import java.util.Optional;

public interface DogService {
    public DogEntity createDog(String name, String breed);
    public Optional<DogEntity> getDog(Long id);
}
