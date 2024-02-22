package com.example.thesis.factory;

import com.example.thesis.dto.AdminRoleStageApproveDTO;
import com.example.thesis.dto.RoleStageApproveDTO;
import com.example.thesis.entity.RoleStageApprove;
import com.example.thesis.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleStageApproveFactory {

    private final EntityMapper entityMapper;

    public RoleStageApproveDTO roleStageApproveDTO(RoleStageApprove approve) {
        return entityMapper.toRoleStageApproveDTO(approve);
    }

    public List<AdminRoleStageApproveDTO> toAdminRoleStageApproveDTOList(List<RoleStageApprove> all) {
        return entityMapper.toAdminRoleStageApproveDTOList(all) ;
    }
    public AdminRoleStageApproveDTO toAdminRoleStageApproveDTO(RoleStageApprove approve) {
        return entityMapper.toAdminRoleStageApproveDTO(approve);
    }
}
