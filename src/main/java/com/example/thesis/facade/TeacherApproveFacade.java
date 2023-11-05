package com.example.thesis.facade;

import com.example.thesis.dto.TeacherStageApproveDTO;
import com.example.thesis.service.TeacherApproveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class TeacherApproveFacade {

    private final TeacherApproveService teacherApproveService;

    @Transactional(readOnly = true)
    public Set<Long> findApproveStageIdSetByTeacherId(Long teacherId) {
        return teacherApproveService.findIdSetByTeacherId(teacherId);
    }

    @Transactional
    public void create (TeacherStageApproveDTO approveDTO) {
        teacherApproveService.save(approveDTO.getTeacherId(),approveDTO.getStageId());
    }
}
