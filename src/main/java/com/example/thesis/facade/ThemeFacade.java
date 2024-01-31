package com.example.thesis.facade;

import com.example.thesis.entity.Student;
import com.example.thesis.entity.enums.Language;
import com.example.thesis.exception.ForbiddenActionException;
import com.example.thesis.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ThemeFacade {

    private final ThemeService themeService;
    private final SecurityFacade securityFacade;

    public void create (Language language, String theme, Student student) {
        themeService.createTheme(language, theme, student);
    }

    @Transactional
    public Boolean changeTheme(String theme, Long id) {
        var editorRole = securityFacade.getCurrentUser().getRole().getName();

        switch (editorRole)  {
            case "HoD" -> {
                var entity = themeService.findById(id);
                entity.setTheme(theme);
                themeService.save(entity);
            }
            case "student", "teacher" -> {
                var entity = themeService.findById(id);
                if (entity.getDeadLineDate().isAfter(LocalDate.now())) {
                    throw new ForbiddenActionException("Deadline is due");
                }
                entity.setTheme(theme);
                themeService.save(entity);
            }
        }
        return true;
    }
}
