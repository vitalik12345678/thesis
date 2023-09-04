package com.example.thesis.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRegistrationDTO {

    @NotBlank(message = "first name mustn't be blank")
    private String firstName;

    @NotBlank(message = "last name mustn't be blank")
    private String lastName;

    @NotBlank(message = "email mustn't be blank")
    @Email
    private String email;

    @NotBlank(message = "password mustn't be blank")
    private String password;

}
