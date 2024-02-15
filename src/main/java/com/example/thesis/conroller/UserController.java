package com.example.thesis.conroller;

import com.example.thesis.dto.*;
import com.example.thesis.exception.ForbiddenActionException;
import com.example.thesis.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public FullUserInfoDTO getCurrentUser() {
        return userFacade.getCurrentUser();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<FullUserInfoDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userFacade.findById(id));
    }

    @GetMapping(value = "/all")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userFacade.findAll());
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('HoD')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            userFacade.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ForbiddenActionException("User has relations so we cannot delete it");
        }
    }

    @PostMapping(value = "/student")
    @PreAuthorize("hasAnyAuthority('HoD')")
    public ResponseEntity<Void> createStudent(@RequestBody StudentRegistrationDTO studentRegistrationDTO) {
        userFacade.saveStudent(studentRegistrationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/teacher")
    @PreAuthorize("hasAnyAuthority('HoD')")
    public ResponseEntity<Void> createStudent(@RequestBody TeacherRegistrationHODDTO teacherRegistrationDTO) {
        userFacade.saveTeacher(teacherRegistrationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/student/{id}")
    @PreAuthorize("hasAnyAuthority('HoD')")
    public ResponseEntity<Void> updateStudent(@PathVariable Long id,@RequestBody StudentUpdateHodDTO dto) {
        userFacade.updateHodStudent(dto,id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/teacher/{id}")
    @PreAuthorize("hasAnyAuthority('HoD')")
    public ResponseEntity<Void> updateTeacher(@PathVariable Long id,@RequestBody TeacherUpdateHodDTO dto) {
        userFacade.updateHodTeacher(dto,id);
        return ResponseEntity.ok().build();
    }

}
