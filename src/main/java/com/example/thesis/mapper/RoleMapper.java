package com.example.thesis.mapper;

import com.example.thesis.dto.RoleDTO;
import com.example.thesis.entity.Role;

import java.util.List;

public interface RoleMapper {

    RoleDTO toRoleDTO(Role role);

    Role toRole(RoleDTO roleDTO);

    List<RoleDTO> toRoleDTOList(List<Role> roleList);
}
