package com.example.thesis.service.impl;

import com.example.thesis.dto.TeacherStudentRequestCreateDTO;
import com.example.thesis.entity.TeacherStudentRequest;
import com.example.thesis.repository.StudentTeacherRequestRepository;
import com.example.thesis.service.StudentService;
import com.example.thesis.service.StudentTeacherRequestService;
import com.example.thesis.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentTeacherRequestServiceImpl extends CRUDServiceImpl<TeacherStudentRequest,Long> implements StudentTeacherRequestService {

    private final StudentTeacherRequestRepository repository;
    private final StudentService studentService;
    private final TeacherService teacherService;


    @Override
    protected JpaRepository<TeacherStudentRequest, Long> getRepository () {
        return repository;
    }

    @Override
    public TeacherStudentRequest add (Long studentId, Long teacherId, TeacherStudentRequestCreateDTO dto) {

        var student = studentService.findById(studentId);
        var teacher = teacherService.findById(teacherId);

        if (Objects.nonNull(findByTeacherAndStudentId(teacherId, studentId))) {
            throw new RuntimeException("Request exits");
        }

        var request = new TeacherStudentRequest();
        request.setStudent(student);
        request.setTeacher(teacher);
        request.setTheme(dto.getTheme());

        return save(request);
    }

    @Override
    @Transactional
    public TeacherStudentRequest findByTeacherAndStudentId (Long teacherId, Long studentId) {
        return repository.findByTeacherIdAndStudentId(teacherId,studentId).orElseThrow( () -> new RuntimeException("Reqeust doenst' exist") );
    }
}
