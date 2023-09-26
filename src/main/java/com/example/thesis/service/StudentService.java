package com.example.thesis.service;

import com.example.thesis.entity.Student;

import java.util.Optional;

public interface StudentService extends CRUDService<Student,Long> {
    Student findByUserId (Long userId);

    Optional<Student> findByUserIdOpt (Long userId);
}
