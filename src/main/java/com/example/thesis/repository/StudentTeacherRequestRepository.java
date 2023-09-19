package com.example.thesis.repository;

import com.example.thesis.entity.TeacherStudentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentTeacherRequestRepository extends JpaRepository<TeacherStudentRequest,Long> {
    Optional<TeacherStudentRequest> findByTeacherIdAndStudentId (Long teacherId, Long studentId);
}
