package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequestDTO {
    private Long teacherId;
    private String firstName;
    private String lastName;
    private RoleDTO roleDTO;
}
