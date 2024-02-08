package com.example.thesis.facade;

import com.example.thesis.dto.RoleDTO;
import com.example.thesis.factory.RoleFactory;
import com.example.thesis.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleFacade {

    private final RoleService roleService;
    private final RoleFactory roleFactory;

    @Transactional(readOnly = true)
    public RoleDTO findByName(String name) {
        return roleFactory.toRoleDTO(roleService.findByName(name));
    }

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        return roleFactory.toRoleDTOList(roleService.findAll());
    }
}
