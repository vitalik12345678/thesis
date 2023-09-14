package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentFileInfoDTO {

    private String documentId;
    private Boolean approved;
    private String originalName;
    private LocalDateTime createdDate;
    private LocalDateTime approvedDate;

}
