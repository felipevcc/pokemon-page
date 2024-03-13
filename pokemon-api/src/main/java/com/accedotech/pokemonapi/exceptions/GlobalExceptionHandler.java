package com.accedotech.pokemonapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(UserNotFoundException e) {
        e.fillInStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User doesn't exists");
    }

    @ExceptionHandler(value = InvalidPasswordException.class)
    public ResponseEntity<String> invalidPasswordException(InvalidPasswordException e) {
        e.fillInStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @ExceptionHandler(value = EmailNotAvailableException.class)
    public ResponseEntity<String> emailNotAvailableException(EmailNotAvailableException e) {
        e.fillInStackTrace();
        return ResponseEntity.status(HttpStatus.CONFLICT).body("The email is already associated with another account");
    }
}
