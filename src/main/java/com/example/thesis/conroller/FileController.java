package com.example.thesis.conroller;

import com.example.thesis.dto.DocumentDTO;
import com.example.thesis.dto.FileDTO;
import com.example.thesis.facade.DocumentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/file/")
@RequiredArgsConstructor
public class FileController {

    private final DocumentFacade documentFacade;

    @PostMapping("/student/{studentId}")
    private ResponseEntity<DocumentDTO> downloadStudentFile(@PathVariable Long studentId,
                                                            @RequestParam MultipartFile document) {

       return new ResponseEntity<>(documentFacade.downloadFile(document,studentId),HttpStatus.CREATED);

    }

    @GetMapping("/{documentId}")
    public ResponseEntity<Resource> getFileByContentId(@PathVariable Long documentId) {
        var fileDTO = documentFacade.getFileByContentId(documentId);

        var header = new HttpHeaders();
        header.set("fullName", fileDTO.getFullName());

        return new ResponseEntity<>(fileDTO.getResource(), header, HttpStatus.OK);
    }

    @PutMapping("/{documentId}")
    public ResponseEntity<?> updateApprovedStatus(@PathVariable Long documentId,
                                                  @RequestParam Boolean isApproved) {
        return ResponseEntity.ok(documentFacade.updateApprovedStatus(documentId,isApproved));
    }

    @PutMapping("{documentId}/move-to-next-stage/{stageId}")
    public ResponseEntity<?> moveToNextStage(@PathVariable Long documentId,
                                             @PathVariable Long stageId) {
        return ResponseEntity.ok(documentFacade.moveToNextStage(documentId,stageId));
    }

}
