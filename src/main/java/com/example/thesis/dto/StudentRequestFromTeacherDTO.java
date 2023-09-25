package com.example.thesis.dto;

import com.example.thesis.entity.enums.Language;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestFromTeacherDTO {

    private String requestId;
    private String createdDate;
    private String theme;
    private Boolean approved;
    private Language language;
    private TeacherRequestDTO teacherRequestDTO;
}
