package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DocumentDTO {

    private String documentId;
    private String contentId;
    private String studentId;
    private String approved;
    private String originalName;
    private StageDTO stageDTO;
    private LocalDateTime approvedDate;
    private LocalDateTime createdDate;

}
