package com.example.thesis.conroller;

import com.example.thesis.entity.User;
import com.example.thesis.security.UserPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {




    @GetMapping(value = "/current")
    @PreAuthorize("permitAll()")
    public User getCurrentUser(@AuthenticationPrincipal UserPrincipal user) {
        return user.getUser();
    }

}
