package com.dogshelter.dog_shelter_app.domain.request;

import lombok.Data;

import java.util.Date;

@Data
public class CreateAppointmentRequest {
    private Date startDate;
    private Date endDate;
    private Long clientId;
    private String firstName = null;
    private String lastName = null;
    private String phoneNumber = null;
    private Long dogId;
}

