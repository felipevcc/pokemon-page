package com.accedotech.pokemonapi.service.imp;

import com.accedotech.pokemonapi.model.User;
import com.accedotech.pokemonapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Class with the method to load the userDetails with the stored user.
 */
@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Get the user stored by his email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User doesn't exist");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPasswordHash())
                .build();
    }
}
