package com.example.thesis.service;

import com.example.thesis.dto.TeacherStudentRequestCreateDTO;
import com.example.thesis.entity.Student;
import com.example.thesis.entity.Teacher;
import com.example.thesis.entity.TeacherStudentRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentTeacherRequestService extends CRUDService<TeacherStudentRequest,Long>{
    TeacherStudentRequest add (Long studentId, Long teacherId, TeacherStudentRequestCreateDTO createDTO);

    @Transactional(readOnly = true)
    Optional<TeacherStudentRequest> findByTeacherAndStudentIdOpt (Long teacherId, Long studentId);

    List<TeacherStudentRequest> findByTeacher (Teacher teacher);

    @Transactional(readOnly = true)
    List<TeacherStudentRequest> findByStudent (Student student);

    TeacherStudentRequest approve (TeacherStudentRequest request);

    void updateThemeByStudentId(Long studentId, String theme);
}
