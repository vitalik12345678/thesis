package com.example.thesis.dto;

import com.example.thesis.entity.enums.Language;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherStudentRequestCreateDTO {
    private String theme;
    private Language language;
}
