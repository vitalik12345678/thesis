package com.example.thesis.service.impl;

import com.example.thesis.entity.Student;
import com.example.thesis.repository.StudentRepository;
import com.example.thesis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends CRUDServiceImpl<Student,Long> implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    protected JpaRepository<Student, Long> getRepository () {
        return this.studentRepository;
    }
}
