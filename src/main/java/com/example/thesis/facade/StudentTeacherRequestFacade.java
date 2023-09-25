package com.example.thesis.facade;

import com.example.thesis.dto.StudentRequestFromTeacherDTO;
import com.example.thesis.dto.StudentTeacherRequestProfileDTO;
import com.example.thesis.dto.TeacherRequestFromStudentDTO;
import com.example.thesis.dto.TeacherStudentRequestCreateDTO;
import com.example.thesis.entity.Student;
import com.example.thesis.entity.Teacher;
import com.example.thesis.factory.StudentTeacherRequestFactory;
import com.example.thesis.service.StudentTeacherRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentTeacherRequestFacade {

    private final StudentTeacherRequestFactory requestFactory;
    private final StudentTeacherRequestService requestService;

    public StudentTeacherRequestProfileDTO createRequest(Long studentId, Long teacherId, TeacherStudentRequestCreateDTO createDTO) {
        return requestFactory.toStudentTeacherRequestProfileDTO( requestService.add(studentId,teacherId,createDTO));
    }

    public List<TeacherRequestFromStudentDTO> findTeacherRequestList (Teacher teacher) {
        return requestFactory.toTeacherRequestFromStudentDTOList(requestService.findByTeacher(teacher));
    }

    public List<StudentRequestFromTeacherDTO> findStudentRequestList (Student student) {
        return requestFactory.toStudentRequestFromTeacherDTOList(requestService.findByStudent(student));
    }

    @Transactional
    public void changeRequestStatus (Long requestId, Boolean approved) {
        var request = requestService.findById(requestId);
        request.setApproved(approved);
        if (approved) {
            requestService.approve(request);
        } else {
            requestService.delete(requestId);
        }
    }

    @Transactional
    public Boolean deleteById (Long requestId) {
        requestService.delete(requestId);
        return true;
    }
}
