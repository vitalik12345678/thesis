package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherUpdateHodDTO extends UserHodCreationDTO {

    private Long generalMaster;
    private Long generalBachelor;

}
