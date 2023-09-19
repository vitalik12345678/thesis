package com.example.thesis.conroller;

import com.example.thesis.dto.TeacherStudentRequestCreateDTO;
import com.example.thesis.facade.SecurityFacade;
import com.example.thesis.facade.TeacherFacade;
import com.example.thesis.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherFacade teacherFacade;
    private final SecurityFacade securityFacade;

    @GetMapping(value = "/all")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getAllTeacherList(){
        return ResponseEntity.ok(teacherFacade.findAllTeacher());
    }

    @PostMapping(value = "request/{studentId}")
    @PreAuthorize("hasAnyAuthority('teacher')")
    public ResponseEntity<?> createStudentTeacherRequest(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                         @RequestBody TeacherStudentRequestCreateDTO createDTO,
                                                         @PathVariable Long studentId) {
        return new ResponseEntity<>(teacherFacade.createStudentTeacherRequest(
                securityFacade.getTeacherByUserId(userPrincipal.getUser().getUserId()).getTeacherId(),
                studentId,createDTO), HttpStatus.CREATED);
    }

}
