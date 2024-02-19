package com.example.thesis.repository;

import com.example.thesis.entity.Student;
import com.example.thesis.entity.Teacher;
import com.example.thesis.entity.TeacherStudentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentTeacherRequestRepository extends JpaRepository<TeacherStudentRequest,Long> {
    Optional<TeacherStudentRequest> findByTeacherIdAndStudentId (Long teacherId, Long studentId);
    Optional<TeacherStudentRequest> findByStudentIdAndApprovedAndTeacherId (Long studentId,Boolean approved,Long teacherId);

    List<TeacherStudentRequest> findAllByTeacher (Teacher teacher);

    List<TeacherStudentRequest> findAllByStudent (Student student);
    List<TeacherStudentRequest> findAllByTeacherIdAndApproved(Long teacherId,Boolean approve);

    @Query(value = " update teacher_student_request SET theme = ?2 WHERE student_id = ?1 AND approved = true ",nativeQuery = true)
    @Modifying
    void updateThemeByStudentId(Long studentId, String theme);

    @Query(value = " update teacher_student_request SET teacher_id = ?2 WHERE student_id = ?1 AND approved = true ",nativeQuery = true)
    @Modifying
    void updateTeacherIdByStudentId(Long studentId, Long teacherId);
}
