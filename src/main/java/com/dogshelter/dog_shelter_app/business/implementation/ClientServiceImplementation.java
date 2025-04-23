package com.dogshelter.dog_shelter_app.business.implementation;

import com.dogshelter.dog_shelter_app.business.ClientService;
import com.dogshelter.dog_shelter_app.persistance.ClientRepository;
import com.dogshelter.dog_shelter_app.persistance.entity.ClientEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImplementation implements ClientService {
    private ClientRepository clientRepository;

    @Override
    public ClientEntity createClient(String firstName, String lastName, String phoneNumber) {
        ClientEntity client = ClientEntity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .build();
        clientRepository.save(client);
        return client;
    }
}
