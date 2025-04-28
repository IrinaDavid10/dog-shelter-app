package com.dogshelter.dog_shelter_app.domain.request;

import lombok.Data;

import java.util.Date;

@Data
public class CreateAppointmentRequest {
    private Date startDate;
    private Date endDate;
    private Long clientId;
    private Long dogId;
}

