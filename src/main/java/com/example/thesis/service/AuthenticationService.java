package com.example.thesis.service;

import com.example.thesis.dto.JwtDTO;
import com.example.thesis.dto.LoginDTO;

public interface AuthenticationService {

    JwtDTO authorize (LoginDTO loginDTO);

}
