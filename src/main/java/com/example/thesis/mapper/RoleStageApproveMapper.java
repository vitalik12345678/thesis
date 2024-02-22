package com.example.thesis.mapper;

import com.example.thesis.dto.AdminRoleStageApproveDTO;
import com.example.thesis.dto.RoleStageApproveDTO;
import com.example.thesis.entity.RoleStageApprove;
import org.mapstruct.Mapping;

import java.util.List;

public interface RoleStageApproveMapper {

    @Mapping(source = "stage.stageId", target = "stageId")
    @Mapping(source = "role.roleId", target = "roleId")
    RoleStageApproveDTO toRoleStageApproveDTO(RoleStageApprove approve);

    @Mapping(source = "stage" ,target = "stageDTO")
    @Mapping(source = "role", target = "roleDTO")
    AdminRoleStageApproveDTO toAdminRoleStageApproveDTO(RoleStageApprove approve);

    List<AdminRoleStageApproveDTO> toAdminRoleStageApproveDTOList(List<RoleStageApprove> all);
}
