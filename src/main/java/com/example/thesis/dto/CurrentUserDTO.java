package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentUserDTO {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private RoleDTO roleDTO;
    private CurrentStudentDTO studentDTO;
    private CurrentTeacherDTO teacherDTO;

}
