package com.dogshelter.dog_shelter_app.controller;

import com.dogshelter.dog_shelter_app.business.AppointmentService;
import com.dogshelter.dog_shelter_app.domain.request.CreateAppointmentRequest;
import com.dogshelter.dog_shelter_app.domain.request.CreateClientRequest;
import com.dogshelter.dog_shelter_app.persistance.entity.AppointmentEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new appointment",
            description = "Schedules a new appointment, linking a client and a dog.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Appointment created successfully",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "Invalid appointment data provided",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "404", description = "Client or Dog not found for the appointment",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "500", description = "Internal server error during appointment creation",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string")))
    })
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