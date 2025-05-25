package com.dogshelter.dog_shelter_app.domain.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DogDTO {
    private Long id;
    private String name;
    private String breed;
}
