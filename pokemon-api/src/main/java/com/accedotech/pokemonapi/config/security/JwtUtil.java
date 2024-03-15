package com.accedotech.pokemonapi.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Class for JWT management.
 * Contains Json Web Token management functionalities.
 */
@Component
public class JwtUtil {

    // Secret key and algorithm used to sign the token
    private static final String SECRET_KEY = "p0k3m0n_4p1";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    /**
     * Method for creating a new JWT.
     * Contains the user's email, the issuer, creation and expiration dates and the signature.
     */
    public String create(String email) {
        return JWT.create()
                .withSubject(email)
                .withIssuer("pokemon-api")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITHM);
    }

    /**
     * Method for JWT validation.
     */
    public boolean isValid(String jwt) {
        try {
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * Method to obtain the user's email from the Token subject.
     */
    public String getUserEmail(String jwt) {
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
    }
}
