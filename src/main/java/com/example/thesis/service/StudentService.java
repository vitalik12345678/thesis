package com.example.thesis.service;

import com.example.thesis.entity.Student;

public interface StudentService extends CRUDService<Student,Long> {
    Student findByUserId (Long userId);
}
