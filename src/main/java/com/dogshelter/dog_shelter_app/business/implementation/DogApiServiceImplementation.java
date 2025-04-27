package com.dogshelter.dog_shelter_app.business.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class DogApiServiceImplementation {
    private final RestTemplate restTemplate;

    @Value("${dog.api.url}")
    private String dogApiUrl;

    public DogApiServiceImplementation(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    public String fetchRandomImage(){
        try{
            Map<String,String> response = restTemplate.getForObject(dogApiUrl,Map.class);
            if(response != null && response.containsKey("message")){
                return response.get("message");
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
