package com.example.thesis.service.impl;

import com.example.thesis.entity.RoleStageApprove;
import com.example.thesis.exception.ExistException;
import com.example.thesis.repository.RoleStageApproveRepository;
import com.example.thesis.service.RoleService;
import com.example.thesis.service.RoleStageApproveService;
import com.example.thesis.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleStageApproveServiceImpl extends CRUDServiceImpl<RoleStageApprove,Long> implements RoleStageApproveService  {

    private final RoleStageApproveRepository roleStageApproveRepository;
    private final RoleService roleService;
    private final StageService stageService;

    @Override
    protected JpaRepository<RoleStageApprove, Long> getRepository() {
        return roleStageApproveRepository;
    }

    @Override
    @Transactional
    public RoleStageApprove create(Long roleId, Long stageId) {
        if (findByRoleAndStageIdsOpt(roleId, stageId).isPresent()) {
            throw new ExistException("Role has approve for that stage ");
        }
        RoleStageApprove approve = new RoleStageApprove();
        approve.setRole(roleService.findById(roleId));
        approve.setStage(stageService.findById(stageId));
        return save(approve);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RoleStageApprove> findByRoleAndStageIdsOpt(Long roleId, Long stageId) {
        return roleStageApproveRepository.findByRoleIdAndStageId(roleId,stageId);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Long> findStageIdSetByRoleId(Long roleId) {
        return new HashSet<>(roleStageApproveRepository.findAllByRoleId(roleId));
    }
}


