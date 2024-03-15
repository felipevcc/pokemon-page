package com.accedotech.pokemonapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Class to handle all exceptions.
 * Contains existing and custom exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Exception handling method when a user does not exist in storage.
     */
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(UserNotFoundException e) {
        e.fillInStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User doesn't exist");
    }

    /**
     * Exception handling method when password does not match.
     */
    @ExceptionHandler(value = InvalidPasswordException.class)
    public ResponseEntity<String> invalidPasswordException(InvalidPasswordException e) {
        e.fillInStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    /**
     * Exception handling method when the email is linked to another user.
     */
    @ExceptionHandler(value = EmailNotAvailableException.class)
    public ResponseEntity<String> emailNotAvailableException(EmailNotAvailableException e) {
        e.fillInStackTrace();
        return ResponseEntity.status(HttpStatus.CONFLICT).body("The email is already associated with another account");
    }

    /**
     * Method to handle the exception when the PokeAPI service is not responding.
     */
    @ExceptionHandler(value = PokeAPINotAvailableException.class)
    public ResponseEntity<String> pokeAPINotAvailableException(PokeAPINotAvailableException e) {
        e.fillInStackTrace();
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("PokeAPI is not responding, try again later");
    }

    /**
     * Exception handling method when a pokémon does not exist in PokeAPI service storage.
     */
    @ExceptionHandler(value = PokemonNotFoundException.class)
    public ResponseEntity<String> pokemonNotFoundException(PokemonNotFoundException e) {
        e.fillInStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokémon doesn't exists");
    }

    /**
     * Method to handle the exception when a request input is invalid.
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentException(IllegalArgumentException e) {
        e.fillInStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * Method to handle the exception when a request input is invalid.
     */
    @ExceptionHandler(value = ForbiddenAccessException.class)
    public ResponseEntity<String> forbiddenAccessException(ForbiddenAccessException e) {
        e.fillInStackTrace();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Insufficient permissions for this action");
    }
}
