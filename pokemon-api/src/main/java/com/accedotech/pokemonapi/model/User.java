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
    @Column(name = "first_name")
    private String firstName;

    // User's last name
    @Column(name = "last_name")
    private String lastName;

    // Encrypted user password
    @Column(name = "password_hash")
    private String passwordHash;

    // User's mobile phone number
    @Column(name = "phone_number")
    private String phoneNumber;

    // User's email address
    @Column(name = "email")
    private String email;

    // User creation date
    @Column(name = "created_at")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
}
