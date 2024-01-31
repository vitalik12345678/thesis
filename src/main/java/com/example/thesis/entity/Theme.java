package com.example.thesis.entity;

import com.example.thesis.entity.enums.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
public class Theme implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theme_id")
    private Long themeId;

    @Column(name = "theme")
    private String theme;

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "deadline_date")
    private LocalDate deadLineDate;

    @Override
    public Long getId () {
        return getThemeId();
    }

    @OneToOne(mappedBy = "theme")
    private Student student;

}
