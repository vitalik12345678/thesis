package com.example.thesis.dto;

import com.example.thesis.entity.enums.Degree;
import com.example.thesis.entity.enums.Language;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentAdviserStudentDTO {

    private Degree degree;
    private Language language;
    private String firstName;
    private String lastName;
    private String faculty;
    private String cluster;
    private String graduateDate;
    private String theme;

}
