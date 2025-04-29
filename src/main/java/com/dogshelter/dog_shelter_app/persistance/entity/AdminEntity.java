package com.dogshelter.dog_shelter_app.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "admin")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="user_name")
    private String username;
    @Column(name="user_password")
    private String password;
    @Column(name="user_role")
    private Set<String>roles;

}
