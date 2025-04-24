package com.dogshelter.dog_shelter_app.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class DogDTO {
    private Long id;
    private String name;
    private String breed;
}
