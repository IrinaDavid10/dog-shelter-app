package com.dogshelter.dog_shelter_app.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dog")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DogEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name="dog_name", length=50)
    private String name;
    @NotBlank
    @Column(name = "dog_breed", length =50)
    private String breed;
    private String imageUrl;
    @OneToMany(mappedBy = "dogEntity")
    private Set<AppointmentEntity> appointmentEntitySet = new HashSet<>();
}
