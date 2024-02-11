package com.example.thesis.dto;

import com.example.thesis.entity.enums.Degree;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentDTO {

    private Long studentId;
    private Degree degree;
    private String cluster;
    private String faculty;
    private LocalDate graduateDate;

}
