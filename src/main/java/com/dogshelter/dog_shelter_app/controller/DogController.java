package com.dogshelter.dog_shelter_app.controller;

import com.dogshelter.dog_shelter_app.business.DogService;
import com.dogshelter.dog_shelter_app.domain.dto.DogDTO;
import com.dogshelter.dog_shelter_app.domain.request.CreateDogRequest;
import com.dogshelter.dog_shelter_app.persistance.entity.DogEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dogs")
@AllArgsConstructor
public class DogController {
    private final DogService dogService;

    @Operation(summary = "Create a new dog entry",
            description = "Allows an ADMIN to create a new dog in the shelter database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dog created successfully",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "Invalid request payload or data validation failure",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required"),
            @ApiResponse(responseCode = "403", description = "Forbidden - Only ADMIN role can access this resource")
    })
    @RolesAllowed({"ADMIN"})
    @PostMapping("/create")
    public ResponseEntity<String> createDog(@Valid @RequestBody CreateDogRequest request) {
        DogEntity dog = DogEntity.builder()
                .name(request.getName())
                .breed(request.getBreed()).build();
        DogEntity doggy = dogService.createDog(dog.getName(), dog.getBreed());
        if (doggy != null) {
            return ResponseEntity.ok("Successfully created a dog.");
        }
        return ResponseEntity.ok("Could not create dog.");

    }
    @Operation(summary = "Get a dog by ID",
            description = "Retrieves a single dog's details by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dog found successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DogDTO.class))),
            @ApiResponse(responseCode = "404", description = "Dog not found with the given ID")
    })
    @GetMapping("/{id}")
    public Optional<DogDTO> getDog(@PathVariable Long id) {
        Optional<DogDTO> dog = dogService.getDog(id);
        return dog;
    }
    @Operation(summary = "Get all dogs",
            description = "Retrieves a list of all dogs in the shelter.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of dogs",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DogDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required"),
            @ApiResponse(responseCode = "403", description = "Forbidden - Only ADMIN role can access this resource")
    })
    @GetMapping()
    @RolesAllowed({"ADMIN"})
    public Optional<List<DogDTO>> getAllDogs() {
        Optional<List<DogDTO>> dogDTOList = dogService.getAllDogs();
        return dogDTOList;
    }
    @Operation(summary = "Delete a dog by ID",
            description = "Deletes a dog entry from the shelter database by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dog deleted successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Dog not found with the given ID"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required"),
            @ApiResponse(responseCode = "403", description = "Forbidden - Consider adding role-based access for delete")
    })
    @DeleteMapping("/{id}")
    public boolean deleteDog(@PathVariable Long id) {
        return dogService.deleteDog(id);

    }
}
