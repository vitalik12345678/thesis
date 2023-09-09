package com.example.thesis.facade;

import com.example.thesis.dto.TeacherStudentRequestCreateDTO;
import com.example.thesis.factory.StudentTeacherRequestFactory;
import com.example.thesis.service.StudentTeacherRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentTeacherRequestFacade {

    private final StudentTeacherRequestFactory requestFactory;
    private final StudentTeacherRequestService requestService;

    public Object createRequest(Long studentId, Long teacherId, TeacherStudentRequestCreateDTO createDTO) {
        return requestService.add(studentId,teacherId,createDTO);
    }

}
