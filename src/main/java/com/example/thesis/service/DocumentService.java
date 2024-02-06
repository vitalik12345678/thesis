package com.example.thesis.service;

import com.example.thesis.entity.Document;
import com.example.thesis.entity.Stage;
import com.example.thesis.entity.enums.ApproveStatus;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface DocumentService extends CRUDService<Document,Long> {
    Document downloadFile (MultipartFile file, Long studentId);

    Resource getFileByContentId (Document document);

    Optional<Document> findByStudentIdAndApprovedOpt (Long studentId, Boolean isApproved,ApproveStatus status);

    Document updateApprovedStatus (Long documentId, Boolean isApproved, ApproveStatus rejected);

    Document changeStage (Long documentId, Long stageId);

    List<Document> findAllByStudentId (Long studentId);

    List<Document> findAllByStage (Stage stage);
}
