package com.example.thesis.repository;

import com.example.thesis.entity.Student;
import com.example.thesis.entity.Teacher;
import com.example.thesis.entity.TeacherStudentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentTeacherRequestRepository extends JpaRepository<TeacherStudentRequest,Long> {
    Optional<TeacherStudentRequest> findByTeacherIdAndStudentId (Long teacherId, Long studentId);

    List<TeacherStudentRequest> findAllByTeacher (Teacher teacher);

    List<TeacherStudentRequest> findAllByStudent (Student student);
}
