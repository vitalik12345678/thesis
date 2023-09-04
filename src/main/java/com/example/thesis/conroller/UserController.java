package com.example.thesis.conroller;

import com.example.thesis.dto.CurrentUserDTO;
import com.example.thesis.entity.User;
import com.example.thesis.facade.UserFacade;
import com.example.thesis.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;


    @GetMapping(value = "/current")
    @PreAuthorize("permitAll()")
    public CurrentUserDTO getCurrentUser() {
        return userFacade.getCurrentUser();
    }
}
