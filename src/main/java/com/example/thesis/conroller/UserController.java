package com.example.thesis.conroller;

import com.example.thesis.dto.CurrentUserDTO;
import com.example.thesis.dto.UserDTO;
import com.example.thesis.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userFacade.findById(id));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userFacade.findAll());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userFacade.deleteById(id);
        return ResponseEntity.ok().build();
    }




}
