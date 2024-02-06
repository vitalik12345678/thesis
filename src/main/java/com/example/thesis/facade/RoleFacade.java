package com.example.thesis.facade;

import com.example.thesis.dto.RoleDTO;
import com.example.thesis.factory.RoleFactory;
import com.example.thesis.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleFacade {

    private final RoleService roleService;
    private final RoleFactory roleFactory;

    public RoleDTO findByName(String name) {
        return roleFactory.toRoleDTO(roleService.findByName(name));
    }
}
