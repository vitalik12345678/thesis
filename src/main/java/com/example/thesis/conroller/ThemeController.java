package com.example.thesis.conroller;

import com.example.thesis.facade.ThemeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/theme")
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeFacade themeFacade;

    @PutMapping("/{studentId}")
    @PreAuthorize("hasAnyAuthority('student','teacher','HoD')")
    public ResponseEntity<Boolean> changeTheme(@RequestParam("theme") String theme, @PathVariable Long studentId) {
        return ResponseEntity.ok(themeFacade.changeTheme(theme,studentId));
    }

}
