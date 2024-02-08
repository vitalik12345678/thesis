package com.example.thesis.factory;

import com.example.thesis.dto.RoleDTO;
import com.example.thesis.entity.Role;
import com.example.thesis.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleFactory {

    private final EntityMapper entityMapper;

    public RoleDTO toRoleDTO(Role role) {
        return entityMapper.toRoleDTO(role);
    }

    public Role toRole(RoleDTO roleDTO) {
        return entityMapper.toRole(roleDTO);
    }

    public List<RoleDTO> toRoleDTOList(List<Role> roleList) {
        return entityMapper.toRoleDTOList(roleList);
    }

}
