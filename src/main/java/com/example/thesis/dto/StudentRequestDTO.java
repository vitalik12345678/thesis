package com.example.thesis.dto;

import com.example.thesis.entity.enums.Degree;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDTO {
    private String firstName;
    private String lastName;
    private Degree degree;
    private String faculty;
    private String cluster;
}
