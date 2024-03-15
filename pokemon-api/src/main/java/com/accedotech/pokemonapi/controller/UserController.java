package com.accedotech.pokemonapi.controller;

import com.accedotech.pokemonapi.config.security.JwtUtil;
import com.accedotech.pokemonapi.dto.user.*;
import com.accedotech.pokemonapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Controller of endpoints related to users.
 * Contains all call inputs and outputs for each endpoint.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Void> userLogin(@RequestBody LoginDTO loginData) {
        // Authentication with its validation
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginData.getEmail(), loginData.getPassword());
        Authentication authentication = authenticationManager.authenticate(login);
        // Generate JWT
        String jwt = jwtUtil.create(loginData.getEmail());
        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.AUTHORIZATION, jwt).build();
    }

    @GetMapping("/getData")
    public ResponseEntity<UserDTO> getUserData() {
        UserDTO user = userService.getUserData();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> userRegistration(@RequestBody UserRegisterDTO userData) {
        UserDTO createdUser = userService.registerUser(userData);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> userUpdate(@RequestBody UserUpdateDTO userData) {
        UserDTO updatedUser = userService.updateUser(userData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<UserDTO> updatePassword(@RequestBody PasswordUpdateDTO passwordUpdateData) {
        UserDTO updatedUser = userService.updatePassword(passwordUpdateData.getNewPassword());
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }
}
