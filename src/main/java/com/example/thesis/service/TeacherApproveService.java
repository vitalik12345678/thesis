package com.example.thesis.service;

import com.example.thesis.entity.TeacherApprove;

import java.util.Set;

public interface TeacherApproveService extends CRUDService<TeacherApprove,Long> {
    Set<Long> findIdSetByTeacherId (Long teacherId);

    TeacherApprove save(Long teacherId,Long stageId);
}
