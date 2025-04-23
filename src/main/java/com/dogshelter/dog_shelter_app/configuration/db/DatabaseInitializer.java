package com.dogshelter.dog_shelter_app.configuration.db;

import com.dogshelter.dog_shelter_app.persistance.DogRepository;
import com.dogshelter.dog_shelter_app.persistance.ClientRepository;
import com.dogshelter.dog_shelter_app.persistance.entity.ClientEntity;
import com.dogshelter.dog_shelter_app.persistance.entity.DogEntity;
import jakarta.transaction.Transactional;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {
    private ClientRepository clientRepository;
    private DogRepository dogRepository;

    public DatabaseInitializer(ClientRepository clientRepository, DogRepository dogRepository) {
        this.clientRepository = clientRepository;
        this.dogRepository = dogRepository;
    }

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void DatabaseInitialData() {
        if (dogRepository.count() == 0 || clientRepository.count() == 0) {
            DogEntity dogEntity1 = DogEntity.builder().name("Bruno").breed("Labradoodle").build();
            DogEntity dogEntity2 = DogEntity.builder().name("Rino").breed("Metis").build();
            DogEntity dog1 = dogRepository.save(dogEntity1);
            DogEntity dog2 = dogRepository.save(dogEntity2);

            ClientEntity clientEntity1 = ClientEntity.builder().firstName("Irina").lastName("David").phoneNumber("0722333222").build();
            ClientEntity clientEntity2 = ClientEntity.builder().firstName("Johhny").lastName("Bravo").phoneNumber("0733222333").build();
            ClientEntity client1 = clientRepository.save(clientEntity1);
            ClientEntity client2 = clientRepository.save(clientEntity2);
        }
    }

}