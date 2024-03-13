package com.accedotech.pokemonapi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that represents the password update.
 */
@Data
@AllArgsConstructor @NoArgsConstructor
public class PasswordUpdateDTO {
    // Old password
    private String currentPassword;

    // New password
    private String newPassword;
}
