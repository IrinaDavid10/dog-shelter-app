package com.dogshelter.dog_shelter_app.controller;

import com.dogshelter.dog_shelter_app.business.AppointmentService;
import com.dogshelter.dog_shelter_app.domain.request.CreateAppointmentRequest;
import com.dogshelter.dog_shelter_app.domain.request.CreateClientRequest;
import com.dogshelter.dog_shelter_app.persistance.entity.AppointmentEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<String> createAppointment(@RequestBody CreateAppointmentRequest request) {
        try {
            AppointmentEntity appointmentEntity = appointmentService.createAppointment(request);
            return new ResponseEntity<>("Appointment created!", HttpStatus.CREATED);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<>("An unexpected error occurred: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}