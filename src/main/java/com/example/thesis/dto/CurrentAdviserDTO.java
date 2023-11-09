package com.example.thesis.dto;

import com.example.thesis.entity.enums.Language;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentAdviserDTO {
    private Language language;
    private String theme;
    private Boolean headApprove;
    private TeacherRequestDTO teacherRequestDTO;
    private StageDTO stageDTO;
}
