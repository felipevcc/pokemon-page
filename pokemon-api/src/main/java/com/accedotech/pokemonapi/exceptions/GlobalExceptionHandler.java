package com.accedotech.pokemonapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Class to handle all exceptions.
 * Contains custom exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Exception handling method when a user does not exist in storage.
     */
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(UserNotFoundException e) {
        e.fillInStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User doesn't exists");
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
}
