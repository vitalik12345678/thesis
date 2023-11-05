package com.example.thesis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "teacher_stage_approve")
@Getter
@Setter
public class TeacherApprove implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "teacher_id")
    @ManyToOne
    private Teacher teacher;

    @JoinColumn(name = "stage_id")
    @ManyToOne
    private Stage stage;

}
