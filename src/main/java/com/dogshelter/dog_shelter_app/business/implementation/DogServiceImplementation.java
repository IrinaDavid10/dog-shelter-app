package com.dogshelter.dog_shelter_app.business.implementation;

import com.dogshelter.dog_shelter_app.business.DogService;
import com.dogshelter.dog_shelter_app.persistance.DogRepository;
import com.dogshelter.dog_shelter_app.persistance.entity.DogEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DogServiceImplementation implements DogService {

    private DogRepository dogRepository;

    @Override
    public DogEntity createDog( String name, String breed) {
        DogEntity dog = DogEntity.builder()
                .name(name)
                .breed(breed).build();
        dogRepository.save(dog);
        return dog;
    }

    @Override
    public Optional<DogEntity> getDog(Long id) {
       Optional<DogEntity> dog = dogRepository.findById(id);
       return dog;
    }
}
