package com.accedotech.pokemonapi.service;

import com.accedotech.pokemonapi.dto.user.LoginDTO;
import com.accedotech.pokemonapi.dto.user.UserDTO;
import com.accedotech.pokemonapi.dto.user.UserRegisterDTO;
import com.accedotech.pokemonapi.dto.user.UserUpdateDTO;

public interface UserService {

    UserDTO userLogin(LoginDTO loginData);

    UserDTO getUserById(Long userId);

    UserDTO registerUser(UserRegisterDTO userData);

    UserDTO updateUser(Long userId, UserUpdateDTO userData);

    UserDTO updatePassword(Long userId, String currentPassword, String newPassword);
}
