package com.example.thesis.dto;

import com.example.thesis.entity.enums.ApproveDirection;
import com.example.thesis.entity.enums.Language;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HoDRequestDTO {

    private Long requestId;
    private Long teacherId;
    private Long studentId;
    private Boolean approved;
    private String theme;
    private LocalDateTime createdDate;
    private Language language;
    private ApproveDirection direction;
    private Boolean headApprove;

}
