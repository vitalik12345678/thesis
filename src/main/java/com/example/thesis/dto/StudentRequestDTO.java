package com.example.thesis.dto;

import com.example.thesis.entity.enums.Degree;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentRequestDTO {

    private String studentId;
    private String firstName;
    private String lastName;
    private Degree degree;
    private String faculty;
    private String cluster;
    private LocalDate graduateDate;
}
