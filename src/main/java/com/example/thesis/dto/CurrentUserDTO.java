package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentUserDTO {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private RoleDTO roleDTO;

}
