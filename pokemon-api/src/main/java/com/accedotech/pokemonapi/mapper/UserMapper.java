package com.accedotech.pokemonapi.mapper;

import com.accedotech.pokemonapi.dto.user.UserDTO;
import com.accedotech.pokemonapi.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMapper {
    @Autowired
    ModelMapper modelMapper;

    public UserDTO userToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
