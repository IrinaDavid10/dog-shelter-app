package com.dogshelter.dog_shelter_app.persistance;

import com.dogshelter.dog_shelter_app.persistance.entity.DogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends JpaRepository<DogEntity,Long> {
}
