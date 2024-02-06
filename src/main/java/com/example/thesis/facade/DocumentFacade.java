package com.example.thesis.facade;

import com.example.thesis.dto.DocumentDTO;
import com.example.thesis.dto.FileDTO;
import com.example.thesis.dto.StageDTO;
import com.example.thesis.entity.enums.ApproveStatus;
import com.example.thesis.exception.ForbiddenActionException;
import com.example.thesis.factory.DocumentFactory;
import com.example.thesis.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentFacade {

    private final DocumentFactory documentFactory;
    private final DocumentService documentService;
    private final TeacherApproveFacade teacherApproveFacade;
    private final SecurityFacade securityFacade;

    @Transactional
    public DocumentDTO downloadFile(MultipartFile file, Long studentId) {
        var document = documentService.downloadFile(file,studentId);
        return documentFactory.toDocumentDTO(document);
    }

    @Transactional(readOnly = true)
    public FileDTO getFileByContentId (Long documentId) {
        var document = documentService.findById(documentId);
        var resource = documentService.getFileByContentId(document);
        var fileDTO = new FileDTO();
        fileDTO.setResource(resource);
        fileDTO.setFullName(document.getOriginalName());
        return fileDTO;
    }

    @Transactional
    public DocumentDTO updateApprovedStatus (Long documentId, Boolean isApproved) {
        if (isApproved) {
            return documentFactory.toDocumentDTO(documentService.updateApprovedStatus(documentId,isApproved, ApproveStatus.APPROVED));
        } else {
            return documentFactory.toDocumentDTO( documentService.updateApprovedStatus(documentId, isApproved,ApproveStatus.REJECTED));
        }
    }

    @Transactional
    public DocumentDTO moveToNextStage (Long documentId, Long stageId) {
        var document = documentService.findById(documentId);
        var availableApprove = teacherApproveFacade.findApproveStageIdSetByTeacherId(securityFacade.getTeacherByUserId(securityFacade.getCurrentUser().getUserId()).getTeacherId());
        if (!availableApprove.contains(document.getStage().getStageId())) {
            throw new ForbiddenActionException("You cannot approve that document");
        }
        return documentFactory.toDocumentDTO(documentService.changeStage(documentId,stageId));
    }

    @Transactional(readOnly = true)
    public List<DocumentDTO> findDocumentListByStudentId (Long studentId) {
        return documentFactory.toDocumentDTOList(documentService.findAllByStudentId(studentId));
    }

    public StageDTO findStageDTOByDocumentId (Long documentId) {
        return documentFactory.toStageDTO(documentService.findById(documentId).getStage());
    }
}
