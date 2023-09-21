package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentTeacherRequestProfileDTO {

    private Long requestId;
    private Boolean approved;
    private LocalDateTime createdDate;
    private String theme;
    private Long teacherId;
    private Long studentId;

}
