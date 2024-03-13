package com.accedotech.pokemonapi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that represents the data for the registration of a new user.
 */
@Data
@AllArgsConstructor @NoArgsConstructor
public class UserRegisterDTO {
    // User's first name
    private String firstName;

    // User's last name
    private String lastName;

    // User's password
    private String password;

    // User's mobile phone number
    private String phoneNumber;

    // User's email address
    private String email;
}
