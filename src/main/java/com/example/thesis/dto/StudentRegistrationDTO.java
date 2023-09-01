package com.example.thesis.dto;

import com.example.thesis.entity.enums.Degree;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentRegistrationDTO extends TeacherRegistrationDTO {

    private String group;
    private String faculty;
    private LocalDate graduateDate;
    private Degree degree;

}
