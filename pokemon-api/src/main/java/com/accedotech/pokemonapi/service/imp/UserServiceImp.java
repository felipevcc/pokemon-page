package com.accedotech.pokemonapi.service.imp;

import com.accedotech.pokemonapi.dto.user.LoginDTO;
import com.accedotech.pokemonapi.dto.user.UserDTO;
import com.accedotech.pokemonapi.dto.user.UserRegisterDTO;
import com.accedotech.pokemonapi.dto.user.UserUpdateDTO;
import com.accedotech.pokemonapi.exceptions.EmailNotAvailableException;
import com.accedotech.pokemonapi.exceptions.InvalidPasswordException;
import com.accedotech.pokemonapi.exceptions.UserNotFoundException;
import com.accedotech.pokemonapi.mapper.UserMapper;
import com.accedotech.pokemonapi.model.User;
import com.accedotech.pokemonapi.repository.UserRepository;
import com.accedotech.pokemonapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
     * Method to give users access to all system functionalities.
     */
    @Override
    public UserDTO userLogin(LoginDTO loginData) {
        // Get the user by email
        User user = userRepository.findByEmail(loginData.getEmail());
        // Validate if the user exists
        if (user == null) {
            throw new UserNotFoundException();
        }
        // Check password
        boolean successfulLogin = passwordEncoder.matches(loginData.getPassword(), user.getPasswordHash());
        if (!successfulLogin) {
            throw new InvalidPasswordException();
        }
        return userMapper.userToDTO(user);
    }

    /**
     * Method to get a user by his unique identifier.
     */
    @Override
    public UserDTO getUserById(Long userId) {
        // Get the requested user
        User foundUser = findUserById(userId);
        return userMapper.userToDTO(foundUser);
    }

    /**
     * Method to register a new user in the system.
     */
    @Override
    public UserDTO registerUser(UserRegisterDTO userData) {
        // Validate if the email is already associated with another account
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
    public UserDTO updateUser(Long userId, UserUpdateDTO userData) {
        // User to update
        User foundUser = findUserById(userId);

        // Validate if the email was updated and is associated with another account
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
    public UserDTO updatePassword(Long userId, String currentPassword, String newPassword) {
        // User to update
        User foundUser = findUserById(userId);

        // Check current password
        if (!passwordEncoder.matches(currentPassword, foundUser.getPasswordHash())) {
            throw new InvalidPasswordException();
        }

        // Save the new password encrypted
        foundUser.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(foundUser);

        return userMapper.userToDTO(foundUser);
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
