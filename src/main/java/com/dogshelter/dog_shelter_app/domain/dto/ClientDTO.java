package com.dogshelter.dog_shelter_app.domain.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Set<AppointmentDTO> appointmentDTOSet;
}
