package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentFileInfoDTO {

    private Long documentId;
    private Boolean approved;
    private String originalName;
    private LocalDateTime createdDate;
    private LocalDateTime approvedDate;
    private StageDTO stageDTO;

}
