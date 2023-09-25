package com.example.thesis.dto;

import com.example.thesis.entity.enums.Degree;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentStudentDTO {

    private Long studentId;
    private Degree degree;

}
