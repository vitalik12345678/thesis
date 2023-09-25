package com.example.thesis.conroller;

import com.example.thesis.facade.StudentTeacherRequestFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
