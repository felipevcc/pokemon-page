package com.accedotech.pokemonapi.service.imp;

import com.accedotech.pokemonapi.dto.user.UserDTO;
import com.accedotech.pokemonapi.dto.user.UserRegisterDTO;
import com.accedotech.pokemonapi.dto.user.UserUpdateDTO;
import com.accedotech.pokemonapi.exceptions.EmailNotAvailableException;
import com.accedotech.pokemonapi.exceptions.ForbiddenAccessException;
import com.accedotech.pokemonapi.exceptions.UserNotFoundException;
import com.accedotech.pokemonapi.mapper.UserMapper;
import com.accedotech.pokemonapi.model.User;
import com.accedotech.pokemonapi.repository.UserRepository;
import com.accedotech.pokemonapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * User management service.
 * Contains methods with the necessary functionalities for user management.
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    /**
     * Method to get a user by his unique identifier.
     */
    @Override
    public UserDTO getUserData() {
        // Get the authenticated user
        User foundUser = getAuthenticatedUser();
        return userMapper.userToDTO(foundUser);
    }

    /**
     * Method to register a new user in the system.
     */
    @Override
    public UserDTO registerUser(UserRegisterDTO userData) {
        // Validate if the email is already associated with another user
        if (userRepository.findByEmail(userData.getEmail()) != null) {
            throw new EmailNotAvailableException();
        }

        // Password encryption
        String passwordHash = passwordEncoder.encode(userData.getPassword());

        // Creating a new User object
        User newUser = new User();
        newUser.setFirstName(userData.getFirstName());
        newUser.setLastName(userData.getLastName());
        newUser.setPasswordHash(passwordHash);
        newUser.setPhoneNumber(userData.getPhoneNumber());
        newUser.setEmail(userData.getEmail());
        userRepository.save(newUser);

        return userMapper.userToDTO(newUser);
    }

    /**
     * Method to update user information.
     */
    @Override
    public UserDTO updateUser(UserUpdateDTO userData) {
        // User to update
        User foundUser = getAuthenticatedUser();

        // Validate if the email was updated and is associated with another user
        if (!userData.getEmail().equalsIgnoreCase(foundUser.getEmail()) &&
                userRepository.findByEmail(userData.getEmail()) != null) {
            throw new EmailNotAvailableException();
        }

        // Updating the user
        foundUser.setFirstName(userData.getFirstName());
        foundUser.setLastName(userData.getLastName());
        foundUser.setPhoneNumber(userData.getPhoneNumber());
        foundUser.setEmail(userData.getEmail());
        userRepository.save(foundUser);

        return userMapper.userToDTO(foundUser);
    }

    /**
     * Method to update the user's password.
     */
    @Override
    public UserDTO updatePassword(String newPassword) {
        // User to update
        User foundUser = getAuthenticatedUser();

        // Save the new password encrypted
        foundUser.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(foundUser);

        return userMapper.userToDTO(foundUser);
    }

    /**
     * Method to get the current authenticated user.
     */
    private User getAuthenticatedUser() {
        // Get the current authentication of the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Get the email of the authenticated user
        String userEmail = authentication.getName();

        // Return of authenticated user
        return userRepository.findByEmail(userEmail);
    }

    /**
     * Method to get a stored user and validate if it exists.
     */
    private User findUserById(Long userId) {
        User foundUser = userRepository.findById(userId).orElse(null);
        // Validate if user does not exist
        if (foundUser == null) {
            throw new UserNotFoundException();
        }
        return foundUser;
    }
}
