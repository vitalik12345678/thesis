package com.example.thesis.entity;

import com.example.thesis.entity.enums.Degree;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Student extends Contributor implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "degree")
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @OneToOne
    @JoinColumn(name = "theme_id",referencedColumnName = "theme_id",unique = true)
    private Theme theme;

    @ManyToOne
    @JoinColumn(name = "scientific_adviser",referencedColumnName = "teacher_id")
    private Teacher adviser;

    @OneToMany(mappedBy = "student")
    private List<TeacherStudentRequest> requestList;

    @Override
    public Long getId () {
        return getStudentId();
    }
}
