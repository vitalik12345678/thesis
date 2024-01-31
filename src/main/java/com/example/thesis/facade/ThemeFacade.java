package com.example.thesis.facade;

import com.example.thesis.entity.Student;
import com.example.thesis.entity.enums.Language;
import com.example.thesis.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ThemeFacade {

    private final ThemeService themeService;

    public void create (Language language, String theme, Student student) {
        themeService.createTheme(language, theme, student);
    }

    @Transactional
    public Boolean changeTheme(String theme, Long id) {
        var entity = themeService.findById(id);
        entity.setTheme(theme);
        themeService.save(entity);
        return true;
    }
}
