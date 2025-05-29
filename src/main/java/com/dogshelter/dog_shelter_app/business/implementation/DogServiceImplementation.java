package com.dogshelter.dog_shelter_app.business.implementation;

import com.dogshelter.dog_shelter_app.business.DogService;
import com.dogshelter.dog_shelter_app.domain.dto.DogDTO;
import com.dogshelter.dog_shelter_app.domain.mapper.DogMapper;
import com.dogshelter.dog_shelter_app.persistance.DogRepository;
import com.dogshelter.dog_shelter_app.persistance.entity.DogEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DogServiceImplementation implements DogService {

    private DogRepository dogRepository;
    private final DogApiServiceImplementation dogApiServiceImplementation;
    private DogMapper dogMapper;

    @Override
    public DogEntity createDog( String name, String breed) {
        DogEntity dog = DogEntity.builder()
                .name(name)
                .breed(breed).build();
        String imageUrl = dogApiServiceImplementation.fetchRandomImage();
        dog.setImageUrl(imageUrl);
        dogRepository.save(dog);
        return dog;
    }

    @Override
    public Optional<DogDTO> getDog(Long id) {
        return dogRepository.findById(id).map(dogMapper::toDto);
    }

    @Override
    public Optional<List<DogDTO>> getAllDogs() {
        List<DogEntity> dogEntityList = dogRepository.findAll();

        if(dogEntityList.isEmpty()){
            return Optional.empty();
        }

        List<DogDTO> dogDTOList = new ArrayList<>();
        for(DogEntity dog : dogEntityList){
            dogDTOList.add(dogMapper.toDto(dog));
        }
        return Optional.of(dogDTOList);
    }

    @Override
    public boolean deleteDog(Long id) {
        Optional<DogEntity> dog = dogRepository.findById(id);
        if(dog.isPresent()){
            dogRepository.delete(dog.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<DogDTO> updateDog(Long id, DogDTO dogDetailsToUpdate) {
        Optional<DogEntity> existingDogOptional = dogRepository.findById(id);

        if(existingDogOptional.isPresent()){
            DogEntity existingDog = existingDogOptional.get();
            if (dogDetailsToUpdate.getName() != null && !dogDetailsToUpdate.getName().isEmpty()) {
                existingDog.setName(dogDetailsToUpdate.getName());
            }
            if (dogDetailsToUpdate.getBreed() != null && !dogDetailsToUpdate.getBreed().isEmpty()) {
                existingDog.setBreed(dogDetailsToUpdate.getBreed());
            }
            DogEntity updatedDog = dogRepository.save(existingDog);
            return Optional.of(dogMapper.toDto(updatedDog));
        }else{
            return Optional.empty();
        }
    }
}
