package com.example.thesis.config;

import com.example.thesis.entity.User;
import com.example.thesis.security.UserPrincipal;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class CustomAuditorAware implements org.springframework.data.domain.AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor () {

        return Optional.ofNullable( ((UserPrincipal)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUser() );
    }
}
