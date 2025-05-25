package com.dogshelter.dog_shelter_app.domain.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AppointmentDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private ClientDTO clientDTO;
    private DogDTO dogDTO;
}
