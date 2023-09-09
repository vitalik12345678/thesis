package com.example.thesis.service;

import com.example.thesis.entity.Teacher;

public interface TeacherService extends CRUDService<Teacher,Long> {
    Teacher findByUserId (Long userId);
}
