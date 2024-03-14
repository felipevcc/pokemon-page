package com.accedotech.pokemonapi.mapper;

import com.accedotech.pokemonapi.dto.user.UserDTO;
import com.accedotech.pokemonapi.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * User class to map objects from one class to another.
 * Contains the necessary mappings using the Model Mapper dependency.
 */
@Configuration
public class UserMapper {
    @Autowired
    ModelMapper modelMapper;

    /**
     * Method to map objects from the User class to the UserDTO class.
     */
    public UserDTO userToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
