package com.example.thesis.service.impl;

import com.example.thesis.entity.Student;
import com.example.thesis.entity.Theme;
import com.example.thesis.entity.enums.Language;
import com.example.thesis.repository.ThemeRepository;
import com.example.thesis.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl extends CRUDServiceImpl<Theme,Long> implements ThemeService  {

    private final ThemeRepository themeRepository;

    @Override
    protected JpaRepository<Theme, Long> getRepository () {
        return this.themeRepository;
    }

    @Override
    public Theme createTheme (Language language, String theme, Student student) {
        var newTheme = new Theme();
        newTheme.setLanguage(language);
        newTheme.setTheme(theme);
        this.save(newTheme);
        newTheme.setStudent(student);
        return newTheme;
    }
}
