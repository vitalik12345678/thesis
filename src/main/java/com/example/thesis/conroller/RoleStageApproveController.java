package com.example.thesis.conroller;

import com.example.thesis.dto.AdminRoleStageApproveDTO;
import com.example.thesis.dto.RoleStageApproveDTO;
import com.example.thesis.entity.RoleStageApprove;
import com.example.thesis.facade.RoleStageApproveFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/approve")
@RequiredArgsConstructor
public class RoleStageApproveController {

    private final RoleStageApproveFacade roleStageApproveFacade;

    @PostMapping(value = "/{roleId}/{stageId}")
    @PreAuthorize("hasAnyAuthority('HoD','PS')")
    public ResponseEntity<RoleStageApproveDTO> createApprove(@PathVariable Long roleId, @PathVariable Long stageId) {
        return new ResponseEntity<>(roleStageApproveFacade.create(roleId,stageId), HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasAnyAuthority('HoD','PS')")
    public ResponseEntity<List<AdminRoleStageApproveDTO>> getAllApproves() {
        return ResponseEntity.ok(roleStageApproveFacade.findAll());
    }

    @DeleteMapping(value = "/{roleId}/{stageId}")
    @PreAuthorize("hasAnyAuthority('HoD','PS')")
    public ResponseEntity<Optional<RoleStageApprove>> deleteApprove(@PathVariable Long roleId, @PathVariable Long stageId) {
        return ResponseEntity.ok(roleStageApproveFacade.delete(roleId,stageId));
    }

}
