package com.example.thesis.conroller;

import com.example.thesis.dto.StudentFileInfoDTO;
import com.example.thesis.dto.TeacherStudentRequestCreateDTO;
import com.example.thesis.facade.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentFacade studentFacade;

    @GetMapping(value = "{studentId}/file-info")
    public ResponseEntity<List<StudentFileInfoDTO>> getFileInfoById(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentFacade.findFileInfoListById(studentId));
    }

    @PostMapping(value = "{studentId}/request/{teacherId}")
    public ResponseEntity<?> createStudentTeacherRequest(@PathVariable Long studentId,
                                                         @PathVariable Long teacherId,
                                                         @RequestBody TeacherStudentRequestCreateDTO createDTO) {
        return new ResponseEntity<>(studentFacade.createStudentTeacherRequest(studentId,teacherId,createDTO), HttpStatus.CREATED);
    }
}
