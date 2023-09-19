package com.example.thesis.service;

import com.example.thesis.dto.TeacherStudentRequestCreateDTO;
import com.example.thesis.entity.TeacherStudentRequest;
import org.springframework.transaction.annotation.Transactional;

public interface StudentTeacherRequestService extends CRUDService<TeacherStudentRequest,Long>{
    TeacherStudentRequest add (Long studentId, Long teacherId, TeacherStudentRequestCreateDTO createDTO);

    @Transactional
    TeacherStudentRequest findByTeacherAndStudentId (Long teacherId, Long studentId);
}
