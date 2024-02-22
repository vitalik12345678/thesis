package com.example.thesis.repository;

import com.example.thesis.entity.RoleStageApprove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleStageApproveRepository extends JpaRepository<RoleStageApprove,Long> {


    Optional<RoleStageApprove> findByRoleIdAndStageId(Long roleId, Long stageId);

    @Query(value = "SELECT a.stage.stageId FROM RoleStageApprove a WHERE a.role.roleId = ?1")
    List<Long> findAllByRoleId(Long roleId);
}
