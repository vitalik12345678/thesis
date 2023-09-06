package com.example.thesis.conroller;

import com.example.thesis.dto.StudentFileInfoDTO;
import com.example.thesis.facade.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
