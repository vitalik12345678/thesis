package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class TeacherDTO {

    private Long teacherId;
    private Long generalBachelor;
    private Long generalMaster;
    private List<Long> availableStageIdSet;

}
