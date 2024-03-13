package com.accedotech.pokemonapi.controller;

import com.accedotech.pokemonapi.dto.user.*;
import com.accedotech.pokemonapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller of endpoints related to users.
 * Contains all call inputs and outputs for each endpoint.
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> userLogin(@RequestBody LoginDTO loginData) {
        UserDTO user = userService.userLogin(loginData);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> userRegistration(@RequestBody UserRegisterDTO userData) {
        UserDTO createdUser = userService.registerUser(userData);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> userUpdate(
            @PathVariable Long id,
            @RequestBody UserUpdateDTO userData
    ) {
        UserDTO updatedUser = userService.updateUser(id, userData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @PutMapping("updatePassword/{id}")
    public ResponseEntity<UserDTO> updatePassword(
            @PathVariable Long id,
            @RequestBody PasswordUpdateDTO passwordUpdateData
    ) {
        UserDTO updatedUser = userService.updatePassword(
                id,
                passwordUpdateData.getCurrentPassword(),
                passwordUpdateData.getNewPassword()
        );
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }
}
