package com.example.thesis.service.impl;

import com.example.thesis.entity.Role;
import com.example.thesis.exception.NotExistObjectException;
import com.example.thesis.repository.RoleRepository;
import com.example.thesis.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends CRUDServiceImpl<Role, Long>  implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    protected JpaRepository<Role, Long> getRepository () {
        return roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Role findByName (String name) {
        return roleRepository.findByName(name).orElseThrow( () -> new NotExistObjectException("Role with name %s doesn't exist".formatted(name)));
    }

    @Override
    @Transactional(readOnly = true)
    public Role getReferenceById (Long id) {
        return roleRepository.getReferenceById(id);
    }
}
