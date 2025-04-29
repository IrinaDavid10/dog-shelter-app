package com.dogshelter.dog_shelter_app.business.implementation;

import com.dogshelter.dog_shelter_app.business.AppointmentService;
import com.dogshelter.dog_shelter_app.domain.request.CreateAppointmentRequest;
import com.dogshelter.dog_shelter_app.persistance.AppointmentRepository;
import com.dogshelter.dog_shelter_app.persistance.ClientRepository;
import com.dogshelter.dog_shelter_app.persistance.DogRepository;
import com.dogshelter.dog_shelter_app.persistance.entity.AppointmentEntity;
import com.dogshelter.dog_shelter_app.persistance.entity.ClientEntity;
import com.dogshelter.dog_shelter_app.persistance.entity.DogEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentServiceImplementation implements AppointmentService {
    private AppointmentRepository appointmentRepository;
    private ClientRepository clientRepository;
    private DogRepository dogRepository;
    @Override
    public AppointmentEntity createAppointment(CreateAppointmentRequest request) {
        ClientEntity clientEntity;
        DogEntity dogEntity;
        if(request.getClientId() == null){
            ClientEntity client = ClientEntity.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .phoneNumber(request.getPhoneNumber())
                    .build();
           clientEntity = clientRepository.save(client);
        }else{
            Optional<ClientEntity> optionalClientEntity = clientRepository.findById(request.getClientId());
            if(optionalClientEntity.isEmpty()){
                throw new NoSuchElementException("No such client");

            }
            clientEntity = optionalClientEntity.get();
        }
        if(request.getDogId() == null){
            throw new NoSuchElementException("No such dog");
        }else {
            Optional<DogEntity> optionalDogEntity = dogRepository.findById(request.getDogId());
            if(optionalDogEntity.isEmpty()){
                throw new NoSuchElementException("No such dog");
            }
            dogEntity = optionalDogEntity.get();
        }

            AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                    .startDate(request.getStartDate())
                    .endDate(request.getEndDate())
                    .clientEntity(clientEntity)
                    .dogEntity(dogEntity)
                    .build();

        appointmentRepository.save(appointmentEntity);
        return appointmentEntity;
    }
}
