package com.example.thesis.facade;

import com.example.thesis.entity.Student;
import com.example.thesis.entity.Teacher;
import com.example.thesis.service.StudentService;
import com.example.thesis.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SecurityFacade {

    private final StudentService studentService;
    private final TeacherService teacherService;

    public Student getStudentByUserId(Long userId) {
        return studentService.findByUserId(userId);
    }

    public Teacher getTeacherByUserId(Long userId) {
        return teacherService.findByUserId(userId);
    }

}
