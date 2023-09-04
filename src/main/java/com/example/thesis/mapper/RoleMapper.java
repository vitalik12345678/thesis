package com.example.thesis.mapper;

import com.example.thesis.dto.RoleDTO;
import com.example.thesis.entity.Role;

public interface RoleMapper {

    RoleDTO toRoleDTO(Role role);

    Role toRole(RoleDTO roleDTO);

}
