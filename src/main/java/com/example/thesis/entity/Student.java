package com.example.thesis.entity;

import com.example.thesis.entity.enums.Degree;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Student implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "degree")
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @Column(name = "cluster")
    private String cluster;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "graduateDate")
    private LocalDate graduateDate;

    @OneToOne
    @JoinColumn(name = "theme_id",referencedColumnName = "theme_id",unique = true)
    private Theme theme;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "scientific_adviser",referencedColumnName = "teacher_id")
    private Teacher adviser;

    @OneToMany(mappedBy = "student",fetch = FetchType.EAGER)
    private List<Document> documentList;

    @OneToMany(mappedBy = "student")
    private List<TeacherStudentRequest> requestList;

    @Override
    public Long getId () {
        return getStudentId();
    }
}
