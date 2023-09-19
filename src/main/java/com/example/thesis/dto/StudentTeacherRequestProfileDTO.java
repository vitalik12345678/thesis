package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentTeacherRequestProfileDTO {

    private Long requestId;
    private Boolean approved;
    private String theme;
    private Long teacherId;
    private Long studentId;

}
