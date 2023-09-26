package com.example.thesis.dto;

import com.example.thesis.entity.enums.ApproveDirection;
import com.example.thesis.entity.enums.Language;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestFromTeacherDTO {

    private Long requestId;
    private String createdDate;
    private String theme;
    private Boolean approved;
    private Language language;
    private ApproveDirection direction;
    private TeacherRequestDTO teacherRequestDTO;
}
