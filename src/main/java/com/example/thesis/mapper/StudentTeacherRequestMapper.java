package com.example.thesis.mapper;

import com.example.thesis.dto.*;
import com.example.thesis.entity.TeacherStudentRequest;
import org.mapstruct.Mapping;

import java.util.List;

public interface StudentTeacherRequestMapper {
    @Mapping(source = "student.studentId",target = "studentId")
    @Mapping(source = "teacher.teacherId",target = "teacherId")
    StudentTeacherRequestProfileDTO toStudentTeacherRequestProfileDTO (TeacherStudentRequest request);

    @Mapping(source = "student",target = "studentRequestDTO")
    TeacherRequestFromStudentDTO toTeacherRequestFromStudentDTO (TeacherStudentRequest byTeacher);

    List<TeacherRequestFromStudentDTO> toTeacherRequestFromStudentDTOList (List<TeacherStudentRequest> byTeacher);

    List<StudentRequestFromTeacherDTO> toStudentRequestFromTeacherDTOList (List<TeacherStudentRequest> byStudent);

    @Mapping(source = "teacher",target = "teacherRequestDTO")
    StudentRequestFromTeacherDTO toStudentRequestFromTeacherDTO (TeacherStudentRequest request);

    List<CurrentAdviserStudentDTO> toCurrentAdviserStudentDTOList (List<TeacherStudentRequest> studentList);


    @Mapping(source = "student",target = "studentRequestDTO")
    CurrentAdviserStudentDTO toCurrentAdviserStudentDTO (TeacherStudentRequest student);

    List<HoDRequestDTO> toHoDRequestDTOList (List<TeacherStudentRequest> requestList);

    @Mapping(source = "student.studentId",target = "studentId")
    @Mapping(source = "teacher.teacherId",target = "teacherId")
    HoDRequestDTO toHoDRequestDTO (TeacherStudentRequest request);

}
