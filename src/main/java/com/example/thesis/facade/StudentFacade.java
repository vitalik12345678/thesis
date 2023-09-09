package com.example.thesis.facade;

import com.example.thesis.dto.CurrentStudentDTO;
import com.example.thesis.dto.StudentFileInfoDTO;
import com.example.thesis.dto.StudentRegistrationDTO;
import com.example.thesis.dto.TeacherStudentRequestCreateDTO;
import com.example.thesis.entity.User;
import com.example.thesis.factory.StudentFactory;
import com.example.thesis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentFacade {

    private final StudentFactory studentFactory;
    private final StudentService studentService;
    private final DocumentFacade documentFacade;
    private final StudentTeacherRequestFacade requestFacade;

    public void create (StudentRegistrationDTO studentRegistrationDTO, User user) {
        var student = studentFactory.fromStudentRegistrationDTO(studentRegistrationDTO);
        student.setUser(user);
        studentService.save(student);
    }

    public List<StudentFileInfoDTO> findFileInfoListById (Long studentId) {
        return studentFactory.toStudentFileInfoDTOList(documentFacade.findDocumentListByStudentId(studentId));
    }

    public Object createStudentTeacherRequest (Long studentId, Long teacherId, TeacherStudentRequestCreateDTO createDTO) {
        return requestFacade.createRequest(studentId, teacherId, createDTO);
    }

    @Transactional(readOnly = true)
    public CurrentStudentDTO getCurrentStudentDTOByUserId (Long userId) {
        var student = studentService.findByUserId(userId);
        return studentFactory.toCurrentStudentDTO(student);
    }
}
