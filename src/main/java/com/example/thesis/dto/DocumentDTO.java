package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DocumentDTO {

    private Long documentId;
    private String contentId;
    private Long studentId;
    private String approved;
    private String originalName;
    private StageDTO stageDTO;
    private LocalDateTime approvedDate;
    private LocalDateTime createdDate;
}
