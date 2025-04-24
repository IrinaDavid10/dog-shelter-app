package com.dogshelter.dog_shelter_app.domain.mapper;

import com.dogshelter.dog_shelter_app.domain.dto.DogDTO;
import com.dogshelter.dog_shelter_app.persistance.entity.DogEntity;
import org.springframework.stereotype.Component;

@Component
public class DogMapper {
    public DogDTO toDto(DogEntity dogEntity){
        Long id = dogEntity.getId();
        String name = dogEntity.getName();
        String breed = dogEntity.getBreed();
        return new DogDTO(id, name, breed);
    }
}
