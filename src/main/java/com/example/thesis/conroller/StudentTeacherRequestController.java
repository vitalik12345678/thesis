package com.example.thesis.conroller;

import com.example.thesis.dto.HoDRequestDTO;
import com.example.thesis.dto.TeacherStudentRequestCreateDTO;
import com.example.thesis.facade.StudentTeacherRequestFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
@RequiredArgsConstructor
public class StudentTeacherRequestController {

    private final StudentTeacherRequestFacade requestFacade;

    @PutMapping("/{requestId}")
    public ResponseEntity<?> changeRequestStatus(@PathVariable Long requestId,
                                                 @RequestParam Boolean approved) {
        requestFacade.changeRequestStatus(requestId,approved );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{requestId}")
    public ResponseEntity<?> deleteRequest(@PathVariable Long requestId) {
        return ResponseEntity.ok(requestFacade.deleteById(requestId));
    }

/*    @PutMapping("/{requestId}/head-department")
    public ResponseEntity<Void> headChangeStatus(@PathVariable Long requestId,
                                              @RequestParam Boolean approved) {
        requestFacade.headChangeStatus(requestId,approved);
        return ResponseEntity.ok().build();
    }*/

    @PostMapping("/hod/{teacherId}/{studentId}")
    @PreAuthorize("hasAnyAuthority('HoD','PS')")
    public ResponseEntity<?> createHodRelation(@PathVariable Long studentId, @PathVariable Long teacherId, @RequestBody TeacherStudentRequestCreateDTO dto) {
        requestFacade.createApprovedRequest(studentId,teacherId,dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/hod/{teacherId}/{studentId}")
    @PreAuthorize("hasAnyAuthority('HoD','PS')")
    public ResponseEntity<Void> editHodRelation(@PathVariable Long studentId, @PathVariable Long teacherId) {
        requestFacade.updateRequest(studentId,teacherId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/head-department/all")
    @PreAuthorize("hasAuthority('HoD')")
    public ResponseEntity<List<HoDRequestDTO>> findAllRequests() {
        return ResponseEntity.ok( requestFacade.findAllRequestList() );
    }
}
