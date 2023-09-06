package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentDTO {

    private String documentId;
    private String contentId;
    private String studentId;
    private String approved;
    private StageDTO stageDTO;

}
