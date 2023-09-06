package com.example.thesis.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;

@Getter
@Setter
public class FileDTO {
    private String fullName;
    private Resource resource;
}
