package com.dogshelter.dog_shelter_app.domain.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DogDTO {
    private Long id;
    @NotBlank(message = "Dog name cannot be blank")
    private String name;
    @NotBlank(message = "Dog breed cannot be blank")
    private String breed;

}
