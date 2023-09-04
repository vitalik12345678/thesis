package com.example.thesis.service;

import com.example.thesis.entity.Role;
import org.springframework.transaction.annotation.Transactional;

public interface RoleService extends CRUDService<Role,Long> {
    Role findByName (String name);

    @Transactional(readOnly = true)
    Role getReferenceById (Long id);
}
