package com.example.thesis.facade;

import com.example.thesis.entity.Student;
import com.example.thesis.entity.enums.Language;
import com.example.thesis.exception.ForbiddenActionException;
import com.example.thesis.exception.NotExistObjectException;
import com.example.thesis.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
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
    public Boolean changeTheme(String theme, Long studentId) {
        var editorRole = securityFacade.getCurrentUser().getRole().getName();

        var themeIdOpt = themeService.findThemeIdByUserId(studentId);

        if (themeIdOpt.isEmpty()) {
            throw new NotExistObjectException("Student haven't had theme yet");
        }

        var themeId = themeIdOpt.get();
        var entity = themeService.findById(themeId);

        switch (editorRole)  {
            case "HoD" -> {
                entity.setTheme(theme);
                themeService.save(entity);
            }
            case "student", "teacher" -> {
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
