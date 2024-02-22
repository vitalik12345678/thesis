package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminRoleStageApproveDTO {
    private Long id;
    private RoleDTO roleDTO;
    private StageDTO stageDTO;
}
