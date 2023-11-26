package com.example.thesis.service.impl;

import com.example.thesis.entity.Student;
import com.example.thesis.entity.Theme;
import com.example.thesis.entity.enums.Language;
import com.example.thesis.repository.ThemeRepository;
import com.example.thesis.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl extends CRUDServiceImpl<Theme,Long> implements ThemeService  {

    private final ThemeRepository themeRepository;

    @Override
    protected JpaRepository<Theme, Long> getRepository () {
        return this.themeRepository;
    }

    @Override
    @Transactional
    public Theme createTheme (Language language, String theme, Student student) {
        var newTheme = new Theme();
        newTheme.setLanguage(language);
        newTheme.setTheme(theme);
        this.save(newTheme);
        newTheme.setStudent(student);
        student.setTheme(newTheme);
        return newTheme;
    }
}
