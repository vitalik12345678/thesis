package com.example.thesis.factory;

import com.example.thesis.dto.*;
import com.example.thesis.entity.Stage;
import com.example.thesis.entity.Teacher;
import com.example.thesis.entity.TeacherStudentRequest;
import com.example.thesis.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeacherFactory {

    private final EntityMapper entityMapper;

    public Teacher fromTeacherRegistrationDTO(TeacherRegistrationDTO teacherRegistrationDTO) {
        return entityMapper.fromTeacherRegistrationDTO(teacherRegistrationDTO);
    }
    public Teacher fromTeacherRegistrationHODDTO(TeacherRegistrationHODDTO teacherRegistrationDTO) {
        return entityMapper.fromTeacherRegistrationHODDTO(teacherRegistrationDTO);
    }

    public TeacherDTO toCurrentTeacherDTO (Teacher teacher) {
        return entityMapper.toCurrentTeacherDTO(teacher);
    }

    public List<TeacherRequestDTO> toTeacherRequestDTOList (List<Teacher> teacherList) {
        return entityMapper.toTeacherRequestDTOList(teacherList);
    }

    public List<CurrentAdviserStudentDTO> toCurrentAdviserStudentDTOList (List<TeacherStudentRequest> studentList) {
        return entityMapper.toCurrentAdviserStudentDTOList(studentList);
    }

    public CurrentAdviserStudentDTO toCurrentAdviserStudentDTO (TeacherStudentRequest student) {
        return entityMapper.toCurrentAdviserStudentDTO(student);
    }

    public StageDTO toStageDTO (Stage stage) {
        return entityMapper.toStageDTO(stage);
    }

    public Teacher toTeacher(TeacherUpdateHodDTO dto, Teacher teacher) {
        teacher.setGeneralBachelor(dto.getGeneralBachelor());
        teacher.setGeneralMaster(dto.getGeneralMaster());
        return teacher;
    }
}
