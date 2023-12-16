package com.example.thesis.service;

import com.example.thesis.entity.TeacherApprove;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TeacherApproveService extends CRUDService<TeacherApprove,Long> {
    Set<Long> findIdSetByTeacherId (Long teacherId);

    TeacherApprove save(Long teacherId,Long stageId);

    void processBundling (Long stageId);

    void processUnBundling(Long stageId);

    List<TeacherApprove> findTeachersByStageId(Long stageId);

    TeacherApprove findByTeacherAndStageId (Long teacherId, Long stageId);

    Optional<TeacherApprove> findOptByTeacherAndStageId (Long teacherId, Long stageId);
}
