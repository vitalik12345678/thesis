package com.example.thesis.service.impl;

import com.example.thesis.entity.TeacherApprove;
import com.example.thesis.exception.ExistException;
import com.example.thesis.facade.TeacherApproveFacade;
import com.example.thesis.repository.TeacherApproveRepository;
import com.example.thesis.service.StageService;
import com.example.thesis.service.TeacherApproveService;
import com.example.thesis.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class TeacherApproveServiceImpl extends CRUDServiceImpl<TeacherApprove,Long> implements TeacherApproveService {

    private final TeacherApproveRepository teacherApproveRepository;
    private final TeacherService teacherService;
    private final StageService stageService;

    @Override
    protected JpaRepository<TeacherApprove, Long> getRepository () {
        return teacherApproveRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Long> findIdSetByTeacherId (Long teacherId) {
        return teacherApproveRepository.findIdsByTeacherId(teacherId);
    }

    @Override
    @Transactional
    public TeacherApprove save (Long teacherId, Long stageId) {
        if (teacherApproveRepository.existsByTeacherIdAndStageId(teacherId,stageId)) {
            throw new ExistException("You have that access to approve");
        }
        TeacherApprove teacherApprove = new TeacherApprove();
        teacherApprove.setTeacher(teacherService.findById(teacherId));
        teacherApprove.setStage(stageService.findById(stageId));
        return save(teacherApprove);
    }
}
