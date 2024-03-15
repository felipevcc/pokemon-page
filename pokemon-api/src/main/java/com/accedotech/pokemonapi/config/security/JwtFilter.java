package com.accedotech.pokemonapi.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter class to validate the veracity of authentication on request.
 * Contains all the necessary validations to check if the user is authenticated.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Validate that it is a valid header Authorization
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            // Invalid.
            filterChain.doFilter(request, response); // This does not load anything in the context of security
            return;
        }

        // Validate that the JWT is valid
        String jwt = authHeader.split(" ")[1].trim();
        if (!jwtUtil.isValid(jwt)) {
            filterChain.doFilter(request, response); // This does not load anything in the context of security
            return;
        }

        // Load user from UserDetailsService
        String userEmail = jwtUtil.getUserEmail(jwt); // Get user's email from the JWT subject
        User user = (User) userDetailsService.loadUserByUsername(userEmail); // security.core.userdetails user

        // Authentication
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), user.getAuthorities()
        );

        // Configuration details. Obtains only the IP because sessions do not have
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // Console output of authentication
        System.out.println(authenticationToken);

        // Loading authentication in the Spring security context
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
