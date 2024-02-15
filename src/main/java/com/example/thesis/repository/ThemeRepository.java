package com.example.thesis.repository;

import com.example.thesis.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme,Long> {

    @Query(value = "SELECT s.theme.themeId FROM Student s WHERE s.studentId = :userId")
    Long findThemeIdByUserId(Long userId);

}
