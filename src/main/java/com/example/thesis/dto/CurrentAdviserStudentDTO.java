package com.example.thesis.dto;

import com.example.thesis.entity.enums.Degree;
import com.example.thesis.entity.enums.Language;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentAdviserStudentDTO {

    private Language language;
    private String theme;
    private StudentRequestDTO studentRequestDTO;

}
