package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CurrentTeacherDTO {

    private Long teacherId;
    private Long generalBachelor;
    private Long generalMaster;
    private Set<Long> availableStageIdSet;

}
