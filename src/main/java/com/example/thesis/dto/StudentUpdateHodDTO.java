package com.example.thesis.dto;

import com.example.thesis.entity.enums.Degree;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentUpdateHodDTO extends UserHodCreationDTO {

    private String cluster;
    private Degree degree;
    private String faculty;
    private LocalDate graduateDate;

}
