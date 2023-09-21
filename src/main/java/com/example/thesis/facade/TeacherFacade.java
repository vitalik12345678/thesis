package com.example.thesis.facade;

import com.example.thesis.dto.*;
import com.example.thesis.entity.Teacher;
import com.example.thesis.entity.TeacherStudentRequest;
import com.example.thesis.entity.User;
import com.example.thesis.factory.TeacherFactory;
import com.example.thesis.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeacherFacade {

    private final TeacherService teacherService;
    private final TeacherFactory teacherFactory;
    private final StudentTeacherRequestFacade requestFacade;

    public void create(TeacherRegistrationDTO teacherRegistrationDTO, User user) {
        var teacher = teacherFactory.fromTeacherRegistrationDTO(teacherRegistrationDTO);
        teacher.setUser(user);
        teacherService.save(teacher);
    }

    @Transactional(readOnly = true)
    public CurrentTeacherDTO getCurrentTeacherDTOByUserId (Long userId) {
        var teacher = teacherService.findByUserId(userId);
        return teacherFactory.toCurrentTeacherDTO(teacher);
    }

    public List<TeacherRequestDTO> findAllTeacher () {
        var teacherList = teacherService.findAll();
        return teacherFactory.toTeacherRequestDTOList(teacherList);
    }

    @Transactional
    public StudentTeacherRequestProfileDTO createStudentTeacherRequest (Long teacherId, Long studentId, TeacherStudentRequestCreateDTO createDTO) {
        return requestFacade.createRequest(studentId, teacherId, createDTO);
    }

    public List<TeacherRequestFromStudentDTO> getTeacherRequestList (Teacher teacher) {
        return (requestFacade.findTeacherRequestList(teacher));
    }

    public List<CurrentAdviserStudentDTO> findCurrentStudentList (Teacher teacher) {
        var existTeacher = teacherService.findById(teacher.getTeacherId());
        var studentList = existTeacher.getRequestList()
                .stream()
                .filter(TeacherStudentRequest::getApproved)
                .toList();
        var currentStudentDTOList = teacherFactory.toCurrentAdviserStudentDTOList(studentList);
        return currentStudentDTOList;
    }
}
