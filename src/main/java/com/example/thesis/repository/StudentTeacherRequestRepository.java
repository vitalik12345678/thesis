package com.example.thesis.repository;

import com.example.thesis.entity.TeacherStudentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTeacherRequestRepository extends JpaRepository<TeacherStudentRequest,Long> {
}
