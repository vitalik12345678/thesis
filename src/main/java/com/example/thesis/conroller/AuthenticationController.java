package com.example.thesis.conroller;

import com.example.thesis.dto.JwtDTO;
import com.example.thesis.dto.LoginDTO;
import com.example.thesis.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthenticationController {

/*
    private final UserService userService;
*/
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController (AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

/*
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody UserDTO userDTO) {
        log.info("signUp user '{}'", userDTO);

        UserDTO savedUserDTO = userService.create(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
    }*/

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@RequestBody LoginDTO loginDTO) {
        log.trace("authentication for user '{}'", loginDTO.getEmail());

        JwtDTO jwtDTO = authenticationService.authorize(loginDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(jwtDTO);
    }


}
