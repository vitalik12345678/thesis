package com.example.thesis.repository;

import com.example.thesis.entity.TeacherApprove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TeacherApproveRepository extends JpaRepository<TeacherApprove,Long> {

    @Query(value = "SELECT t.id FROM TeacherApprove t WHERE t.teacher.teacherId = :teacherId")
    Set<Long> findIdsByTeacherId(@Param("teacherId")Long teacherId);

    Boolean existsByTeacherIdAndStageId(Long teacherId,Long stageId);
}
