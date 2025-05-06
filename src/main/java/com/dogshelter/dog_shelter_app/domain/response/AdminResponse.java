package com.dogshelter.dog_shelter_app.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponse {
    private String token;
    private String message;
}
