package com.dogshelter.dog_shelter_app.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dog")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DogEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name="dog_name", length=50)
    private String name;
    @NotNull
    @Column(name = "dog_breed", length =50)
    private String breed;
    private String imageUrl;
    @OneToMany(mappedBy = "dogEntity",cascade = CascadeType.ALL)
    private Set<AppointmentEntity> appointmentEntitySet = new HashSet<>();
}
