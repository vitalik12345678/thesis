package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long userId;

    private RoleDTO role;

    private String firstName;

    private String lastName;

    private String email;

}
