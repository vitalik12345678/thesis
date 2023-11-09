package com.example.thesis.conroller;

import com.example.thesis.dto.CurrentAdviserDTO;
import com.example.thesis.dto.StudentFileInfoDTO;
import com.example.thesis.dto.StudentRequestDTO;
import com.example.thesis.dto.TeacherStudentRequestCreateDTO;
import com.example.thesis.facade.AuthenticationFacade;
import com.example.thesis.facade.SecurityFacade;
import com.example.thesis.facade.StudentFacade;
import com.example.thesis.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentFacade studentFacade;
    private final SecurityFacade securityFacade;

    @GetMapping(value = "{studentId}/file-info")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<StudentFileInfoDTO>> getFileInfoById(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentFacade.findFileInfoListById(studentId));
    }

    @PostMapping(value = "request/{teacherId}")
    @PreAuthorize("hasAnyAuthority('student')")
    public ResponseEntity<?> createStudentTeacherRequest(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                         @PathVariable Long teacherId,
                                                         @RequestBody TeacherStudentRequestCreateDTO createDTO) {
        return new ResponseEntity<>(studentFacade.createStudentTeacherRequest(
                securityFacade.getStudentByUserId(userPrincipal.getUser().getUserId()).getStudentId(),teacherId,createDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<StudentRequestDTO>> getAllStudent() {
        return ResponseEntity.ok(studentFacade.findAll());
    }

    @GetMapping(value = "/request")
    @PreAuthorize("hasAnyAuthority('student')")
    public ResponseEntity<?> getStudentRequestByJWT(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(studentFacade.findStudentRequestByStudent(securityFacade.getStudentByUserId(userPrincipal.getUser()
                .getUserId())));
    }

    @GetMapping(value = "/current-adviser")
    @PreAuthorize("hasAuthority('student')")
    public ResponseEntity<CurrentAdviserDTO> getCurrentAdviser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(studentFacade.findCurrentAdviser(securityFacade.getStudentByUserId(userPrincipal.getUser()
                .getUserId())));
    }


}
