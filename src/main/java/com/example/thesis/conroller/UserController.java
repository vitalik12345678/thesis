package com.example.thesis.conroller;

import com.example.thesis.dto.*;
import com.example.thesis.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @PostMapping(value = "/student")
    public ResponseEntity<Void> createStudent(@RequestBody StudentRegistrationDTO studentRegistrationDTO) {
        userFacade.saveStudent(studentRegistrationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/teacher")
    public ResponseEntity<Void> createStudent(@RequestBody TeacherRegistrationHODDTO teacherRegistrationDTO) {
        userFacade.saveTeacher(teacherRegistrationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/student/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable Long id,@RequestBody StudentUpdateHodDTO dto) {
        userFacade.updateHodStudent(dto,id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/teacher/{id}")
    public ResponseEntity<Void> updateTeacher(@PathVariable Long id,@RequestBody TeacherUpdateHodDTO dto) {
        userFacade.updateHodTeacher(dto,id);
        return ResponseEntity.ok().build();
    }

}
