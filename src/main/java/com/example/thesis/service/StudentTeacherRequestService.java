package com.example.thesis.service;

import com.example.thesis.dto.TeacherStudentRequestCreateDTO;
import com.example.thesis.entity.TeacherStudentRequest;

public interface StudentTeacherRequestService extends CRUDService<TeacherStudentRequest,Long>{
    Object add (Long studentId, Long teacherId, TeacherStudentRequestCreateDTO createDTO);
}
