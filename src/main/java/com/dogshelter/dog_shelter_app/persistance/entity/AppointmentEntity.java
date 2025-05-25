package com.dogshelter.dog_shelter_app.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="start_date")
    private Date startDate;
    @Column(name="end_date")
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;
    @ManyToOne
    @JoinColumn(name = "dog_id")
    private DogEntity dogEntity;
}
