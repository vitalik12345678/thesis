package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestFromTeacherDTO {

    private String requestId;
    private String lastName;
    private String firstName;
    private String createdDate;
    private String theme;
    private Boolean approved;
}
