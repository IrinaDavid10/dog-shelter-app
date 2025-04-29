package com.dogshelter.dog_shelter_app.persistance;

import com.dogshelter.dog_shelter_app.persistance.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity,Long> {
    Optional<AdminEntity> findByUsername(String username);
}
