package com.dogshelter.dog_shelter_app.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dog")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DogEntity {
    @Id
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String breed;
}
