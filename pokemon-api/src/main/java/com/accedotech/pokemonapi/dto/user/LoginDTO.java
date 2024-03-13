package com.accedotech.pokemonapi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that represents the login of a user.
 * Contains access credentials.
 */
@Data
@AllArgsConstructor @NoArgsConstructor
public class LoginDTO {
    // User's email
    private String email;

    // Password entered by the user
    private String password;
}
