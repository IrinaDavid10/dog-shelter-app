package com.dogshelter.dog_shelter_app.controller;

import com.dogshelter.dog_shelter_app.business.AdminService;
import com.dogshelter.dog_shelter_app.configuration.db.security.JWTUtil;
import com.dogshelter.dog_shelter_app.domain.request.AdminCreationRequest;
import com.dogshelter.dog_shelter_app.domain.request.AdminLoginRequest;
import com.dogshelter.dog_shelter_app.domain.response.AdminLoginResponse;
import com.dogshelter.dog_shelter_app.persistance.entity.AdminEntity;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Validated
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/saveAdmin")
    public ResponseEntity<String> saveAdmin(@RequestBody @Valid AdminCreationRequest request){
        Long id = adminService.saveAdmin(request);
        String message = "Admin with id: " +id+" saved successfully!";
        return ResponseEntity.ok(message);
    }

    @PostMapping("/loginAdmin")
    public ResponseEntity<AdminLoginResponse> login(@RequestBody AdminLoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        AdminEntity adminEntity = adminService.findByUsername(request.getUsername());
        String token = jwtUtil.generateToken(request.getUsername(), adminEntity.getRoles());
        return ResponseEntity.ok(new AdminLoginResponse(token,"Token generated successfully!"));
    }

}
