package com.example.thesis.service;

import com.example.thesis.entity.Student;
import com.example.thesis.entity.Theme;
import com.example.thesis.entity.enums.Language;

public interface ThemeService extends CRUDService<Theme,Long> {
    Theme createTheme (Language language, String theme, Student student);
}
