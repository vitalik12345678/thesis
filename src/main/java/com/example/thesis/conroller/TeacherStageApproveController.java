package com.example.thesis.conroller;

import com.example.thesis.dto.TeacherStageApproveDTO;
import com.example.thesis.facade.TeacherApproveFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher-stage-approve")
public class TeacherStageApproveController {

    private final TeacherApproveFacade teacherApproveFacade;

    @PostMapping
    @PreAuthorize("hasAuthority('HoD')")
    public ResponseEntity<?> createApprove(@RequestBody TeacherStageApproveDTO approveDTO) {
        teacherApproveFacade.create(approveDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //currentTeacher StageIdList return

}
