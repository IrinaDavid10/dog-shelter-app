package com.dogshelter.dog_shelter_app.business.implementation;

import com.dogshelter.dog_shelter_app.domain.dto.DogDTO;
import com.dogshelter.dog_shelter_app.domain.mapper.DogMapper;
import com.dogshelter.dog_shelter_app.persistance.DogRepository;
import com.dogshelter.dog_shelter_app.persistance.entity.DogEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DogServiceImplementationTest {
    @Mock
    private DogRepository dogRepository;

    @Mock
    private DogApiServiceImplementation dogApiServiceImplementation;

    @Mock
    private DogMapper dogMapper;

    @InjectMocks
    private DogServiceImplementation dogService;

    private DogEntity testDogEntity;
    private DogDTO testDogDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        testDogEntity = DogEntity.builder()
                .id(1L)
                .name("Buddy")
                .breed("Golden Retriever")
                .imageUrl("http://example.com/image.jpg")
                .build();

        testDogDTO = DogDTO.builder()
                .id(1L)
                .name("Buddy")
                .breed("Golden Retriever")
                .build();
    }

    @Test
    void testCreateDog() {
        String name = "Buddy";
        String breed = "Golden Retriever";

        when(dogRepository.save(any(DogEntity.class))).thenReturn(testDogEntity);

        DogEntity result = dogService.createDog(name, breed);

        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(breed, result.getBreed());

        verify(dogRepository, times(1)).save(any(DogEntity.class));
    }

    @Test
    void testGetDog_WhenDogExists() {
        Long dogId = 1L;
        when(dogRepository.findById(dogId)).thenReturn(Optional.of(testDogEntity));
        when(dogMapper.toDto(testDogEntity)).thenReturn(testDogDTO);

        Optional<DogDTO> result = dogService.getDog(dogId);

        assertTrue(result.isPresent());
        assertEquals(testDogDTO.getId(), result.get().getId());
        assertEquals(testDogDTO.getName(), result.get().getName());
        assertEquals(testDogDTO.getBreed(), result.get().getBreed());

        verify(dogRepository, times(1)).findById(dogId);
        verify(dogMapper, times(1)).toDto(testDogEntity);
    }

    @Test
    void testGetDog_WhenDogDoesNotExist() {
        Long dogId = 99L;
        when(dogRepository.findById(dogId)).thenReturn(Optional.empty());

        Optional<DogDTO> result = dogService.getDog(dogId);

        assertFalse(result.isPresent());

        verify(dogRepository, times(1)).findById(dogId);
        verify(dogMapper, never()).toDto(any(DogEntity.class));
    }

    @Test
    void testGetAllDogs_WhenDogsExist() {
        DogEntity secondDog = DogEntity.builder()
                .id(2L)
                .name("Max")
                .breed("Labrador")
                .imageUrl("http://example.com/image2.jpg")
                .build();

        DogDTO secondDogDTO = DogDTO.builder()
                .id(2L)
                .name("Max")
                .breed("Labrador")
                .build();

        List<DogEntity> dogEntities = Arrays.asList(testDogEntity, secondDog);

        when(dogRepository.findAll()).thenReturn(dogEntities);
        when(dogMapper.toDto(testDogEntity)).thenReturn(testDogDTO);
        when(dogMapper.toDto(secondDog)).thenReturn(secondDogDTO);

        Optional<List<DogDTO>> result = dogService.getAllDogs();

        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());
        assertEquals("Buddy", result.get().get(0).getName());
        assertEquals("Max", result.get().get(1).getName());

        verify(dogRepository, times(1)).findAll();
        verify(dogMapper, times(2)).toDto(any(DogEntity.class));
    }

    @Test
    void testGetAllDogs_WhenNoDogsExist() {
        when(dogRepository.findAll()).thenReturn(Collections.emptyList());

        Optional<List<DogDTO>> result = dogService.getAllDogs();

        assertFalse(result.isPresent());

        verify(dogRepository, times(1)).findAll();
        verify(dogMapper, never()).toDto(any(DogEntity.class));
    }

    @Test
    void testDeleteDog_WhenDogExists() {
        Long dogId = 1L;
        when(dogRepository.findById(dogId)).thenReturn(Optional.of(testDogEntity));
        doNothing().when(dogRepository).delete(testDogEntity);

        boolean result = dogService.deleteDog(dogId);

        assertTrue(result);

        verify(dogRepository, times(1)).findById(dogId);
        verify(dogRepository, times(1)).delete(testDogEntity);
    }

    @Test
    void testDeleteDog_WhenDogDoesNotExist() {
        Long dogId = 99L;
        when(dogRepository.findById(dogId)).thenReturn(Optional.empty());

        boolean result = dogService.deleteDog(dogId);

        assertFalse(result);

        verify(dogRepository, times(1)).findById(dogId);
        verify(dogRepository, never()).delete(any(DogEntity.class));
    }

}
