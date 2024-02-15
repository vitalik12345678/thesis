package com.example.thesis.dto;

import com.example.thesis.entity.enums.Language;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ThemeDTO {

    private Long themeId;
    private String theme;
    private Language language;
    private LocalDate date;
    private LocalDate deadLineDate;


}
