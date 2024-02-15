package com.example.thesis.mapper;


import com.example.thesis.dto.ThemeDTO;
import com.example.thesis.entity.Theme;

public interface ThemeMapper {

    ThemeDTO toThemeDTO(Theme theme);

}
