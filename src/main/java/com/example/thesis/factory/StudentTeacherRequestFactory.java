package com.example.thesis.factory;

import com.example.thesis.dto.StudentRequestFromTeacherDTO;
import com.example.thesis.dto.StudentTeacherRequestProfileDTO;
import com.example.thesis.dto.TeacherRequestFromStudentDTO;
import com.example.thesis.entity.TeacherStudentRequest;
import com.example.thesis.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentTeacherRequestFactory {

    private final EntityMapper entityMapper;

    public StudentTeacherRequestProfileDTO toStudentTeacherRequestProfileDTO (TeacherStudentRequest request) {
        return entityMapper.toStudentTeacherRequestProfileDTO(request);
    }

    public List<TeacherRequestFromStudentDTO> toTeacherRequestFromStudentDTOList (List<TeacherStudentRequest> byTeacher) {
        return entityMapper.toTeacherRequestFromStudentDTOList(byTeacher);
    }
    public TeacherRequestFromStudentDTO toTeacherRequestFromStudentDTO (TeacherStudentRequest byTeacher) {
        return entityMapper.toTeacherRequestFromStudentDTO(byTeacher);
    }

    public List<StudentRequestFromTeacherDTO> toStudentRequestFromTeacherDTOList (List<TeacherStudentRequest> byStudent) {
        return entityMapper.toStudentRequestFromTeacherDTOList(byStudent);
    }

    public StudentRequestFromTeacherDTO toStudentRequestFromTeacherDTO (TeacherStudentRequest request) {
        return entityMapper.toStudentRequestFromTeacherDTO(request);
    }

}
