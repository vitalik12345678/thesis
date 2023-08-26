package com.example.thesis.security;

import com.example.thesis.entity.User;
import com.example.thesis.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {

        User existUser = userRepository.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("User doesn't exist with email %s ".formatted(email)));

        return new UserPrincipal(existUser);
    }
}
