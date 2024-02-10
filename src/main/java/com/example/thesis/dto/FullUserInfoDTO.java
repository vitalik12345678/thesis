package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FullUserInfoDTO {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private RoleDTO roleDTO;
    private StudentDTO studentDTO;
    private TeacherDTO teacherDTO;

}
