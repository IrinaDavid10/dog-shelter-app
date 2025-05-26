package com.dogshelter.dog_shelter_app.controller;

import com.dogshelter.dog_shelter_app.business.AdminService;
import com.dogshelter.dog_shelter_app.configuration.security.JWTUtil;
import com.dogshelter.dog_shelter_app.domain.request.AdminCreationRequest;
import com.dogshelter.dog_shelter_app.domain.request.AdminLoginRequest;
import com.dogshelter.dog_shelter_app.domain.response.AdminLoginResponse;
import com.dogshelter.dog_shelter_app.persistance.entity.AdminEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Create a new admin user",
            description = "Registers a new administrator account. This endpoint is typically for initial setup or by another authorized admin.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin saved successfully",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "Invalid admin creation request (e.g., validation errors)",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string")))
    })
    @PostMapping("/saveAdmin")
    public ResponseEntity<String> saveAdmin(@RequestBody @Valid AdminCreationRequest request){
        Long id = adminService.saveAdmin(request);
        String message = "Admin with id: " +id+" saved successfully!";
        return ResponseEntity.ok(message);
    }

    @Operation(summary = "Admin login",
            description = "Authenticates an admin user and returns a JWT token for subsequent authenticated requests.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful, JWT token generated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdminLoginResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid username or password")
    })
    @PostMapping("/loginAdmin")
    public ResponseEntity<AdminLoginResponse> login(@RequestBody AdminLoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        AdminEntity adminEntity = adminService.findByUsername(request.getUsername());
        String token = jwtUtil.generateToken(request.getUsername(), adminEntity.getRoles());
        return ResponseEntity.ok(new AdminLoginResponse(token,"Token generated successfully!"));
    }

}
