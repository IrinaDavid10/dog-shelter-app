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

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentServiceImplementation implements AppointmentService {
    private AppointmentRepository appointmentRepository;
    private ClientRepository clientRepository;
    private DogRepository dogRepository;
    @Override
    public AppointmentEntity createAppointment(CreateAppointmentRequest request) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(request.getClientId());
        Optional<DogEntity> dogEntity = dogRepository.findById(request.getDogId());
        if(clientEntity.isEmpty() || dogEntity.isEmpty()){
            return null;
        }
            AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                    .startDate(request.getStartDate())
                    .endDate(request.getEndDate())
                    .clientEntity(clientEntity.get())
                    .dogEntity(dogEntity.get())
                    .build();

        appointmentRepository.save(appointmentEntity);
        return appointmentEntity;
    }
}
