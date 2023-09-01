package com.example.thesis.service.impl;

import com.example.thesis.entity.Teacher;
import com.example.thesis.repository.TeacherRepository;
import com.example.thesis.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl extends CRUDServiceImpl<Teacher,Long> implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    protected JpaRepository<Teacher, Long> getRepository () {
        return teacherRepository;
    }
}
