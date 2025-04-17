package com.dogshelter.dog_shelter_app.controller;

import com.dogshelter.dog_shelter_app.business.DogService;
import com.dogshelter.dog_shelter_app.domain.request.CreateDogRequest;
import com.dogshelter.dog_shelter_app.persistance.DogRepository;
import com.dogshelter.dog_shelter_app.persistance.entity.DogEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/dogs")
@AllArgsConstructor
public class DogController {
    private final DogService dogService;
    private final DogRepository dogRepository;

    public ResponseEntity<String>createDog(@RequestBody CreateDogRequest request){
        // creaza un caine cu datele din request
        DogEntity dog = DogEntity.builder()
                .name(request.getName())
                .breed(request.getBreed()).build();
        // salveaza cainele in database
        DogEntity doggy = dogService.createDog(dog.getName(), dog.getBreed());
        if(doggy != null){
            return ResponseEntity.ok("Successfully created a dog.");
        }
        return ResponseEntity.ok("Could not create dog.");
        // afiseaza true daca a salvat altfel false



    }

}
