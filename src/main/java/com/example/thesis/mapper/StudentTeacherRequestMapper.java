package com.example.thesis.mapper;

import com.example.thesis.dto.CurrentAdviserStudentDTO;
import com.example.thesis.dto.StudentRequestFromTeacherDTO;
import com.example.thesis.dto.StudentTeacherRequestProfileDTO;
import com.example.thesis.dto.TeacherRequestFromStudentDTO;
import com.example.thesis.entity.TeacherStudentRequest;
import org.mapstruct.Mapping;

import java.util.List;

public interface StudentTeacherRequestMapper {
    @Mapping(source = "student.studentId",target = "studentId")
    @Mapping(source = "teacher.teacherId",target = "teacherId")
    StudentTeacherRequestProfileDTO toStudentTeacherRequestProfileDTO (TeacherStudentRequest request);

    @Mapping(source = "student.user.lastName",target = "lastName")
    @Mapping(source = "student.user.firstName",target = "firstName")
    TeacherRequestFromStudentDTO toTeacherRequestFromStudentDTO (TeacherStudentRequest byTeacher);

    List<TeacherRequestFromStudentDTO> toTeacherRequestFromStudentDTOList (List<TeacherStudentRequest> byTeacher);

    List<StudentRequestFromTeacherDTO> toStudentRequestFromTeacherDTOList (List<TeacherStudentRequest> byStudent);

    @Mapping(source = "teacher.user.lastName",target = "lastName")
    @Mapping(source = "teacher.user.firstName",target = "firstName")
    StudentRequestFromTeacherDTO toStudentRequestFromTeacherDTO (TeacherStudentRequest request);

    List<CurrentAdviserStudentDTO> toCurrentAdviserStudentDTOList (List<TeacherStudentRequest> studentList);

    @Mapping(source = "student.degree",target = "degree")
    @Mapping(source = "student.faculty",target = "faculty")
    @Mapping(source = "student.cluster",target = "cluster")
    @Mapping(source = "student.graduateDate",target = "graduateDate")
    @Mapping(source = "student.user.firstName",target = "firstName")
    @Mapping(source = "student.user.lastName",target = "lastName")
    CurrentAdviserStudentDTO toCurrentAdviserStudentDTO (TeacherStudentRequest student);
}
