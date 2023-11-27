package com.example.thesis.service.impl;

import com.example.thesis.entity.Stage;
import com.example.thesis.entity.TeacherApprove;
import com.example.thesis.exception.ExistException;
import com.example.thesis.exception.NotExistObjectException;
import com.example.thesis.repository.TeacherApproveRepository;
import com.example.thesis.service.StageService;
import com.example.thesis.service.TeacherApproveService;
import com.example.thesis.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
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

    @Override
    @Transactional
    public void processBundling (Long stageId) {

        Stage stage = stageService.findById(stageId);

        teacherService.findAll().stream()
                .filter(item -> item.getUser().getRole().getName().equals("teacher"))
                .forEach( teacher -> {
                    Optional<TeacherApprove> teacherApprove = findOptByTeacherAndStageId(teacher.getTeacherId(),stageId);
                    if (teacherApprove.isEmpty()) {
                        TeacherApprove newApprove = new TeacherApprove();
                        newApprove.setStage(stage);
                        newApprove.setTeacher(teacher);
                        this.save(newApprove);

                    }
                });

    }

    @Override
    public TeacherApprove findByTeacherAndStageId (Long teacherId, Long stageId) {
        return findOptByTeacherAndStageId(teacherId, stageId).orElseThrow(() -> new NotExistObjectException("Teacher approve doesn't exist"));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TeacherApprove> findOptByTeacherAndStageId (Long teacherId, Long stageId) {
        return teacherApproveRepository.findByTeacherIdAndStageId(teacherId, stageId);
    }
}
