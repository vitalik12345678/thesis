package com.example.thesis.service;

import com.example.thesis.entity.Document;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface DocumentService extends CRUDService<Document,Long> {
    Document downloadFile (MultipartFile file, Long studentId);

    Resource getFileByContentId (Document document);

    @Transactional
    Optional<Document> findByStudentIdAndApprovedOpt (Long studentId, Boolean isApproved);

    Document updateApprovedStatus (Long documentId, Boolean isApproved);

    Document changeStage (Long documentId, Long stageId);
}
