package com.example.thesis.conroller;

import com.example.thesis.facade.StageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/stage")
@RestController
@RequiredArgsConstructor
public class StageController {

    private final StageFacade stageFacade;

    @GetMapping(value = "/all")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getStageList() {
        return ResponseEntity.ok(stageFacade.getStageDTOList());
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('teacherf')")
    public ResponseEntity<?> deleteStage(@PathVariable Long id) {
        return ResponseEntity.ok(stageFacade.delete(id));
    }
}
