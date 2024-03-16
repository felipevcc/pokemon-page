package com.accedotech.pokemonapi.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Class for CORS configuration.
 * Cross-Origin Resource Sharing.
 */
@Configuration
public class CorsConfig {
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Allowed origin for the frontend
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
        // Allowed methods
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        // All headers allowed
        corsConfiguration.setAllowedHeaders(List.of("*"));

        corsConfiguration.addExposedHeader("Authorization");

        // Set this CORS configuration on the entire API
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}
