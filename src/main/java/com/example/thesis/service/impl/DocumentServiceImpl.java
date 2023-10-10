package com.example.thesis.service.impl;

import com.example.thesis.entity.Document;
import com.example.thesis.entity.Stage;
import com.example.thesis.entity.enums.ApproveStatus;
import com.example.thesis.exception.ExistException;
import com.example.thesis.repository.DocumentContentStore;
import com.example.thesis.repository.DocumentRepository;
import com.example.thesis.service.DocumentService;
import com.example.thesis.service.StageService;
import com.example.thesis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl extends CRUDServiceImpl<Document, Long> implements DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentContentStore documentContentStore;
    private final StudentService studentService;
    private final StageService stageService;

    @Override
    protected JpaRepository<Document, Long> getRepository () {
        return documentRepository;
    }

    @Override
    @Transactional
    public Document downloadFile (MultipartFile file, Long studentId) {
        if (findByStudentIdAndApprovedOpt(studentId,false,ApproveStatus.WAITING).isPresent()) {
            throw new ExistException("Student has unapproved file");
        }
        Document document = new Document();
        document.setStudent(studentService.findById(studentId));
        document.setOriginalName(file.getOriginalFilename());
        document.setApproved(false);
        document.setStatus(ApproveStatus.WAITING);
        document.setCreatedDate(LocalDateTime.now());
        this.save(document);
        documentContentStore.setContent(document,file.getResource());
        return document;
    }

    @Override
    @Transactional
    public Resource getFileByContentId (Document document) {
        return documentContentStore.getResource(document);
    }

    @Override
    @Transactional
    public Optional<Document> findByStudentIdAndApprovedOpt (Long studentId, Boolean isApproved,ApproveStatus status) {
        return documentRepository.findByStudentIdAndApprovedAndStatus(studentId,isApproved,status);
    }

    @Override
    @Transactional
    public Document updateApprovedStatus (Long documentId, Boolean isApproved, ApproveStatus status) {
        Document document = findById(documentId);
        document.setApproved(isApproved);
        document.setApprovedDate(LocalDateTime.now());
        document.setStatus(status);
        return save(document);
    }

    @Override
    @Transactional
    public Document changeStage (Long documentId, Long stageId) {
        Document document = findById(documentId);
        stageService.changeStageByDocument(document,stageId);
        return save(document);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Document> findAllByStudentId (Long studentId) {
        return documentRepository.findAllByStudentId(studentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Document> findAllByStage (Stage stage) {
        return documentRepository.findAllByStage(stage);
    }
}
