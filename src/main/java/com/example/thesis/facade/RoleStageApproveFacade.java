package com.example.thesis.facade;

import com.example.thesis.dto.AdminRoleStageApproveDTO;
import com.example.thesis.dto.RoleStageApproveDTO;
import com.example.thesis.entity.RoleStageApprove;
import com.example.thesis.factory.RoleStageApproveFactory;
import com.example.thesis.service.RoleStageApproveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RoleStageApproveFacade {

    private final RoleStageApproveService roleStageApproveService;
    private final RoleStageApproveFactory factory;

    @Transactional
    public RoleStageApproveDTO create(Long roleId, Long stageId) {
        return factory.roleStageApproveDTO(roleStageApproveService.create(roleId,stageId));
    }

    @Transactional(readOnly = true)
    public List<AdminRoleStageApproveDTO> findAll() {
        return factory.toAdminRoleStageApproveDTOList(roleStageApproveService.findAll());
    }

    @Transactional(readOnly = true)
    public Set<Long> findStageIdsByRoleId(Long roleId) {
        return roleStageApproveService.findStageIdSetByRoleId(roleId);
    }
}
