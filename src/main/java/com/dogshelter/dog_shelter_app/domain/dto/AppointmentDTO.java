package com.dogshelter.dog_shelter_app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class AppointmentDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private ClientDTO clientDTO;
    private DogDTO dogDTO;
}
