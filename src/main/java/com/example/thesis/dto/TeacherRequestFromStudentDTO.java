package com.example.thesis.dto;

import com.example.thesis.entity.enums.Language;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequestFromStudentDTO {

    private Long requestId;
    private Language language;
    private String createdDate;
    private String theme;
    private Boolean approved;
    private StudentRequestDTO studentRequestDTO;

}
