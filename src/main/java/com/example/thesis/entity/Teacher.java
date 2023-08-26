package com.example.thesis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Teacher implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "general_master")
    private Long generalMaster;

    @Column(name = "general_bachelor")
    private Long generalBachelor;

    @OneToMany(mappedBy = "teacher")
    private List<TeacherStudentRequest> requestList;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;

    @Override
    public Long getId () {
        return getTeacherId();
    }
}
