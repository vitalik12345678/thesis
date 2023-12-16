package com.example.thesis.repository;

import com.example.thesis.entity.TeacherApprove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TeacherApproveRepository extends JpaRepository<TeacherApprove,Long> {

    @Query(value = "SELECT t.stage.stageId FROM TeacherApprove t WHERE t.teacher.teacherId = :teacherId")
    Set<Long> findIdsByTeacherId(@Param("teacherId")Long teacherId);

    Boolean existsByTeacherIdAndStageId(Long teacherId,Long stageId);

    Optional<TeacherApprove> findByTeacherIdAndStageId(Long teacherId, Long stageId);

    List<TeacherApprove> findAllByStageId(Long stageId);
}
