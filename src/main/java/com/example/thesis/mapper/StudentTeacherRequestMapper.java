package com.example.thesis.mapper;

import com.example.thesis.dto.StudentTeacherRequestProfileDTO;
import com.example.thesis.entity.TeacherStudentRequest;
import org.mapstruct.Mapping;

public interface StudentTeacherRequestMapper {
    @Mapping(source = "student.studentId",target = "studentId")
    @Mapping(source = "teacher.teacherId",target = "teacherId")
    StudentTeacherRequestProfileDTO toStudentTeacherRequestProfileDTO (TeacherStudentRequest request);
}
