package com.example.thesis.service;

import com.example.thesis.entity.Teacher;

import java.util.Optional;

public interface TeacherService extends CRUDService<Teacher,Long> {
    Teacher findByUserId (Long userId);

    Optional<Teacher> findByUserIdOpt (Long userId);
}
