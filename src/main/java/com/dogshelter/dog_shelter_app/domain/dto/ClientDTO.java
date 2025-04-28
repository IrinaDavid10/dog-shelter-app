package com.dogshelter.dog_shelter_app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Set<AppointmentDTO> appointmentDTOSet;
}
