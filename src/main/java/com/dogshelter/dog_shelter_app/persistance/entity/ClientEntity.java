package com.dogshelter.dog_shelter_app.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="last_name", length=50)
    private String firstName;
    @Column(name="first_name", length=50)
    private String lastName;
    @Column(name="user_phone", length=50)
    private String phoneNumber;

    @OneToMany(mappedBy = "clientEntity")
    private Set<AppointmentEntity> appointmentEntitySet = new HashSet<>();
}
