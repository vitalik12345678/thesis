package com.example.thesis.facade;

import com.example.thesis.dto.DocumentDTO;
import com.example.thesis.dto.FileDTO;
import com.example.thesis.entity.Document;
import com.example.thesis.factory.DocumentFactory;
import com.example.thesis.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class DocumentFacade {

    private final DocumentFactory documentFactory;
    private final DocumentService documentService;

    @Transactional
    public DocumentDTO downloadFile(MultipartFile file, Long studentId) {
        var document = documentService.downloadFile(file,studentId);
        return documentFactory.toDocumentDTO(document);
    }

    public FileDTO getFileByContentId (Long documentId) {
        var document = documentService.findById(documentId);
        var resource = documentService.getFileByContentId(document);
        var fileDTO = new FileDTO();
        fileDTO.setResource(resource);
        fileDTO.setFullName(document.getOriginalName());
        return fileDTO;
    }

    public DocumentDTO updateApprovedStatus (Long documentId, Boolean isApproved) {
        return documentFactory.toDocumentDTO(documentService.updateApprovedStatus(documentId,isApproved));
    }

    public DocumentDTO moveToNextStage (Long documentId, Long stageId) {
        return documentFactory.toDocumentDTO(documentService.changeStage(documentId,stageId));
    }
}
