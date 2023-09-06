package com.example.thesis.factory;

import com.example.thesis.dto.DocumentDTO;
import com.example.thesis.entity.Document;
import com.example.thesis.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DocumentFactory {

    private final EntityMapper entityMapper;

    public DocumentDTO toDocumentDTO (Document document) {
        return entityMapper.toDocumentDTO(document);
    }

    public List<DocumentDTO> toDocumentDTOList (List<Document> allByStudentId) {
        return entityMapper.toDocumentDTOList(allByStudentId);
    }
}
