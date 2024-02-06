package com.example.thesis.conroller;

import com.example.thesis.dto.TeacherStageApproveDTO;
import com.example.thesis.facade.TeacherApproveFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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

    @DeleteMapping
    @PreAuthorize("hasAuthority('HoD')")
    public ResponseEntity<?> deleteApprove(@RequestBody TeacherStageApproveDTO approveDTO) {
        teacherApproveFacade.delete(approveDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //currentTeacher StageIdList return

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<Set<Long>> getTeacherStageDTOList(@PathVariable Long teacherId) {
        return ResponseEntity.ok(teacherApproveFacade.findApproveStageIdSetByTeacherId(teacherId));
    }

    @PostMapping("/{stageId}/{roleId}")
    @PreAuthorize("hasAuthority('HoD')")
    public ResponseEntity<Void> processBundlingAmongTeacherAndRole(@PathVariable Long stageId, @PathVariable Long roleId) {
        teacherApproveFacade.processBundling(stageId,roleId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{stageId}/{roleId}")
    @PreAuthorize("hasAuthority('HoD')")
    public ResponseEntity<Void> processUnBundlingAmongTeacherAndRole(@PathVariable Long stageId, @PathVariable Long roleId) {
        teacherApproveFacade.processUnBundling(stageId,roleId);
        return ResponseEntity.ok().build();
    }
}
