package com.accedotech.pokemonapi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that represents the user's updated information.
 */
@Data
@AllArgsConstructor @NoArgsConstructor
public class UserUpdateDTO {
    // User's first name
    private String firstName;

    // User's last name
    private String lastName;

    // User's mobile phone number
    private String phoneNumber;

    // User's email address
    private String email;
}
