package com.dogshelter.dog_shelter_app.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "appointment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="appointment_date")
    private Date appointmentDate;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;
    @ManyToOne
    @JoinColumn(name = "dog_id")
    private DogEntity dogEntity;
}
