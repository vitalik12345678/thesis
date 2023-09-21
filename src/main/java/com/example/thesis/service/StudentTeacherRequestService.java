package com.example.thesis.service;

import com.example.thesis.dto.TeacherStudentRequestCreateDTO;
import com.example.thesis.entity.Student;
import com.example.thesis.entity.Teacher;
import com.example.thesis.entity.TeacherStudentRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentTeacherRequestService extends CRUDService<TeacherStudentRequest,Long>{
    TeacherStudentRequest add (Long studentId, Long teacherId, TeacherStudentRequestCreateDTO createDTO);

    @Transactional
    TeacherStudentRequest findByTeacherAndStudentId (Long teacherId, Long studentId);

    List<TeacherStudentRequest> findByTeacher (Teacher teacher);

    @Transactional(readOnly = true)
    List<TeacherStudentRequest> findByStudent (Student student);

    TeacherStudentRequest approve (TeacherStudentRequest request);
}
