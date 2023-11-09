package com.example.thesis.facade;

import com.example.thesis.dto.*;
import com.example.thesis.entity.Student;
import com.example.thesis.entity.Teacher;
import com.example.thesis.entity.User;
import com.example.thesis.entity.enums.ApproveDirection;
import com.example.thesis.exception.ForbiddenActionException;
import com.example.thesis.factory.StudentTeacherRequestFactory;
import com.example.thesis.service.StudentTeacherRequestService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class StudentTeacherRequestFacade {

    private final StudentTeacherRequestFactory requestFactory;
    private final StudentTeacherRequestService requestService;
    private final SecurityFacade securityFacade;
    private final ThemeFacade themeFacade;

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

        User user = securityFacade.getCurrentUser();
        if (approved) {
            if (request.getDirection().equals(ApproveDirection.TEACHER) && securityFacade.getStudentByUserIdOpt(user.getUserId()).isPresent()) {
                throw new ForbiddenActionException("You as student cannot approve that request");
            }
            else if (request.getDirection().equals(ApproveDirection.STUDENT) && securityFacade.getTeacherByUserIdOpt(user.getUserId()).isPresent()) {
                throw new ForbiddenActionException("You as teacher cannot approve that request");
            }
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

    @Transactional
    public void headChangeStatus (Long requestId, Boolean approved) {
        var request = requestService.findById(requestId);
        request.setApproved(approved);
        if (approved) {
            requestService.save(request);
            themeFacade.create(request.getLanguage(),request.getTheme(),request.getStudent());
        } else {
            requestService.delete(requestId);
        }
    }

    @Transactional(readOnly = true)
    public List<HoDRequestDTO> findAllRequestList () {
        return requestFactory.toHoDRequestDTOList(requestService.findAll());
    }
}
