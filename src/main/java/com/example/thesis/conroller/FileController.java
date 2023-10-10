package com.example.thesis.conroller;

import com.example.thesis.dto.DocumentDTO;
import com.example.thesis.facade.DocumentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ContentDisposition;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("api/file/")
@RequiredArgsConstructor
public class FileController {

    private final DocumentFacade documentFacade;


    @PostMapping("/student/{studentId}")
    @PreAuthorize("hasAuthority('student')")
    public ResponseEntity<DocumentDTO> downloadStudentFile(@PathVariable Long studentId,
                                                            @RequestParam MultipartFile document) {

       return new ResponseEntity<>(documentFacade.downloadFile(document,studentId),HttpStatus.CREATED);

    }

    @GetMapping(value = "/{documentId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<byte[]> getFileByContentId(@PathVariable Long documentId) {
        var fileDTO = documentFacade.getFileByContentId(documentId);

        var header = new HttpHeaders();
        if (fileDTO.getFullName().endsWith(".pdf")) header.setContentType(MediaType.APPLICATION_PDF);
        else header.setContentType(MediaType.MULTIPART_MIXED);
        header.setContentDisposition(ContentDisposition.inline().filename(URLEncoder.encode(fileDTO.getFullName(), StandardCharsets.UTF_8)).build());

        try {
            return new ResponseEntity<>(fileDTO.getResource().getContentAsByteArray(), header, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{documentId}")
    @PreAuthorize("hasAuthority('teacher')")
    public ResponseEntity<?> updateApprovedStatus(@PathVariable Long documentId,
                                                  @RequestParam Boolean isApproved) {
        return ResponseEntity.ok(documentFacade.updateApprovedStatus(documentId,isApproved));
    }

    @PutMapping(value = "{documentId}/move-to-next-stage/{stageId}")
    @PreAuthorize("hasAuthority('teacher')")
    public ResponseEntity<?> moveToNextStage(@PathVariable Long documentId,
                                             @PathVariable Long stageId) {
        return ResponseEntity.ok(documentFacade.moveToNextStage(documentId,stageId));
    }

}
