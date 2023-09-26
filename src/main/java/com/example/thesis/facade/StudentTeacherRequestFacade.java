package com.example.thesis.facade;

import com.example.thesis.dto.StudentRequestFromTeacherDTO;
import com.example.thesis.dto.StudentTeacherRequestProfileDTO;
import com.example.thesis.dto.TeacherRequestFromStudentDTO;
import com.example.thesis.dto.TeacherStudentRequestCreateDTO;
import com.example.thesis.entity.Student;
import com.example.thesis.entity.Teacher;
import com.example.thesis.entity.User;
import com.example.thesis.entity.enums.ApproveDirection;
import com.example.thesis.exception.ForbiddenActionException;
import com.example.thesis.factory.StudentTeacherRequestFactory;
import com.example.thesis.service.StudentTeacherRequestService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
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

            if (request.getDirection().equals(ApproveDirection.TEACHER) && Objects.nonNull(securityFacade.getStudentByUserId(user.getUserId()))) {
                throw new ForbiddenActionException("You cannot approve that request");
            }
            else if (request.getDirection().equals(ApproveDirection.STUDENT) && Objects.nonNull(securityFacade.getTeacherByUserId(user.getUserId()))) {
                throw new ForbiddenActionException("You cannot approve that request");
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
}
