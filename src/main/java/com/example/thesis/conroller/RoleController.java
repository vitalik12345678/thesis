package com.example.thesis.conroller;

import com.example.thesis.dto.RoleDTO;
import com.example.thesis.facade.RoleFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleFacade roleFacade;

    @GetMapping(value = "/all")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return ResponseEntity.ok( roleFacade.findAll() );
    }


}
