package com.dogshelter.dog_shelter_app.controller;

import com.dogshelter.dog_shelter_app.business.ClientService;
import com.dogshelter.dog_shelter_app.domain.request.CreateClientRequest;
import com.dogshelter.dog_shelter_app.persistance.entity.ClientEntity;
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

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @Operation(summary = "Create a new client",
            description = "Registers a new client with their first name, last name, and phone number.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client created successfully",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "400", description = "Invalid client data provided",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error, could not create client",
                    content = @Content(mediaType = "text/plain"))
    })
    @PostMapping("/create")
    public ResponseEntity<String>createClient(@RequestBody CreateClientRequest request){

        ClientEntity client = ClientEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .build();
        ClientEntity client1 = clientService.createClient(client.getFirstName(), client.getLastName(), client.getPhoneNumber());
        if(client1 != null){
            return ResponseEntity.ok("Successfully created a client.");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not create client.");
    }
}
