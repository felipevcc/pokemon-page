package com.accedotech.pokemonapi.service;

import com.accedotech.pokemonapi.dto.user.LoginDTO;
import com.accedotech.pokemonapi.dto.user.UserDTO;
import com.accedotech.pokemonapi.dto.user.UserRegisterDTO;
import com.accedotech.pokemonapi.dto.user.UserUpdateDTO;

/**
 * Interface that represents the user management service.
 * Contains methods with the necessary functionalities for user management.
 */
public interface UserService {

    // User login method
    UserDTO userLogin(LoginDTO loginData);

    // Method to get a user by id
    UserDTO getUserById(Long userId);

    // Method to register a new user in the system
    UserDTO registerUser(UserRegisterDTO userData);

    // Method to update user information
    UserDTO updateUser(Long userId, UserUpdateDTO userData);

    // Method to update the user's password
    UserDTO updatePassword(Long userId, String currentPassword, String newPassword);
}
