package com.example.thesis.mapper;

import com.example.thesis.dto.DocumentDTO;
import com.example.thesis.entity.Document;
import org.mapstruct.Mapping;

public interface DocumentMapper {

    @Mapping(source = "stage",target = "stageDTO")
    @Mapping(source = "student.studentId",target = "studentId")
    DocumentDTO toDocumentDTO (Document document);
}
