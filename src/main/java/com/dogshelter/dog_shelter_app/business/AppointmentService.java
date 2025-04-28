package com.dogshelter.dog_shelter_app.business;

import com.dogshelter.dog_shelter_app.domain.request.CreateAppointmentRequest;
import com.dogshelter.dog_shelter_app.persistance.entity.AppointmentEntity;

public interface AppointmentService {
    public AppointmentEntity createAppointment (CreateAppointmentRequest request);
}
