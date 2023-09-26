package com.example.thesis.facade;

import com.example.thesis.entity.Student;
import com.example.thesis.entity.Teacher;
import com.example.thesis.entity.User;
import com.example.thesis.security.UserPrincipal;
import com.example.thesis.service.StudentService;
import com.example.thesis.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SecurityFacade {

    private final StudentService studentService;
    private final TeacherService teacherService;

    public Student getStudentByUserId(Long userId) {
        return studentService.findByUserId(userId);
    }
    public Optional<Student> getStudentByUserIdOpt(Long userId) {
        return studentService.findByUserIdOpt(userId);
    }

    public Teacher getTeacherByUserId(Long userId) {
        return teacherService.findByUserId(userId);
    }
    public Optional<Teacher> getTeacherByUserIdOpt(Long userId) {
        return teacherService.findByUserIdOpt(userId);
    }

    public User getCurrentUser(){
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

}
