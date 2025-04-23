package com.dogshelter.dog_shelter_app.controller;

import com.dogshelter.dog_shelter_app.business.ClientService;
import com.dogshelter.dog_shelter_app.domain.request.CreateClientRequest;
import com.dogshelter.dog_shelter_app.persistance.entity.ClientEntity;
import lombok.AllArgsConstructor;
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
        return ResponseEntity.ok("Could not create client.");
    }
}
