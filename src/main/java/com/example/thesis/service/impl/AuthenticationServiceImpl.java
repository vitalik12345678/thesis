package com.example.thesis.service.impl;

import com.example.thesis.dto.JwtDTO;
import com.example.thesis.dto.LoginDTO;
import com.example.thesis.security.jwt.JwtUtil;
import com.example.thesis.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl (JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public JwtDTO authorize (LoginDTO loginDTO) throws AuthenticationException {
        System.out.println(new BCryptPasswordEncoder().encode(loginDTO.getPassword()));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtUtil.generateToken(userDetails);

        JwtDTO jwtDTO = new JwtDTO();
        jwtDTO.setAccessToken(token);
        return jwtDTO;
    }

}
