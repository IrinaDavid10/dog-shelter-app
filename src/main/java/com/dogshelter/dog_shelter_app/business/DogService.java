package com.dogshelter.dog_shelter_app.business;

import com.dogshelter.dog_shelter_app.domain.dto.DogDTO;
import com.dogshelter.dog_shelter_app.persistance.entity.DogEntity;

import java.util.List;
import java.util.Optional;

public interface DogService {
    public DogEntity createDog(String name, String breed);
    public Optional<DogDTO> getDog(Long id);
    public Optional<List<DogDTO>> getAllDogs();
    public boolean deleteDog(Long id);
    public Optional<DogDTO> updateDog(Long id, DogDTO dogDetailsToUpdate);

}
