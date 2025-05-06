package com.dogshelter.dog_shelter_app.business;

import com.dogshelter.dog_shelter_app.persistance.entity.AdminEntity;

import java.util.Optional;

public interface AdminService {
    Long saveAdmin(AdminEntity adminEntity);
    AdminEntity findByUsername(String username);
}
