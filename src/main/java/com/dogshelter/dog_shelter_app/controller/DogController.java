package com.dogshelter.dog_shelter_app.controller;

import com.dogshelter.dog_shelter_app.business.DogService;
import com.dogshelter.dog_shelter_app.domain.dto.DogDTO;
import com.dogshelter.dog_shelter_app.domain.request.CreateDogRequest;
import com.dogshelter.dog_shelter_app.persistance.entity.DogEntity;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dogs")
@AllArgsConstructor
public class DogController {
    private final DogService dogService;

    @PostMapping("/create")
    public ResponseEntity<String>createDog(@Valid @RequestBody CreateDogRequest request){
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
        // afiseaza ceva pt ca a salvat

    }
    @GetMapping("/{id}")
    public Optional<DogDTO> getDog(@PathVariable Long id){
        Optional<DogDTO> dog = dogService.getDog(id);
        return dog;
    }

    @GetMapping()
    public Optional<List<DogDTO>> getAllDogs(){
        Optional<List<DogDTO>> dogDTOList = dogService.getAllDogs();
        return dogDTOList;
    }

}
