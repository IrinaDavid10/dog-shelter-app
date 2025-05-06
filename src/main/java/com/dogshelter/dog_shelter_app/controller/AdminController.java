package com.dogshelter.dog_shelter_app.controller;

import com.dogshelter.dog_shelter_app.business.AdminService;
import com.dogshelter.dog_shelter_app.configuration.db.security.JWTUtil;
import com.dogshelter.dog_shelter_app.domain.request.AdminRequest;
import com.dogshelter.dog_shelter_app.domain.response.AdminResponse;
import com.dogshelter.dog_shelter_app.persistance.entity.AdminEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/saveAdmin")
    public ResponseEntity<String> saveAdmin(@RequestBody AdminEntity adminEntity){
        Long id = adminService.saveAdmin(adminEntity);
        String message = "Admin with id: " +id+" saved successfully!";
        return ResponseEntity.ok(message);
    }

    @PostMapping("/loginAdmin")
    public ResponseEntity<AdminResponse> login(@RequestBody AdminRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AdminResponse(token,"Token generated successfully!"));
    }

    @PostMapping("/getData")
    public ResponseEntity<String> testAfterLogin(Principal p){
        return ResponseEntity.ok("You're data after a valid login. You are: "+p.getName());
    }
}
