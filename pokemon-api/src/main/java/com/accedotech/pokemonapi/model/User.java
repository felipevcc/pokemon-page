package com.accedotech.pokemonapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * User table class.
 * Contains all user information stored in the database.
 */
@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "user")
public class User {
    // Unique user identifier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    // User's first name
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    // User's last name
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    // Encrypted user password
    @Column(name = "password_hash", nullable = false, length = 100)
    private String passwordHash;

    // User's mobile phone number
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    // User's email address
    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    // User creation date
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
}
