package com.example.thesis.service;

import com.example.thesis.entity.RoleStageApprove;

import java.util.Optional;
import java.util.Set;

public interface RoleStageApproveService extends CRUDService<RoleStageApprove,Long> {
    RoleStageApprove create(Long roleId, Long stageId);

    Optional<RoleStageApprove> findByRoleAndStageIdsOpt(Long roleId, Long stageId);

    Set<Long> findStageIdSetByRoleId(Long roleId);

}
