package com.example.thesis.entity;

import com.example.thesis.entity.enums.ApproveDirection;
import com.example.thesis.entity.enums.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "teacher_student_request")
@Getter
@Setter
public class TeacherStudentRequest implements EntityWithId<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "head_approve")
    private Boolean headApprove;

    @Column(name = "approve_direction")
    @Enumerated(EnumType.STRING)
    private ApproveDirection direction;

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(name = "theme")
    private String theme;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Override
    public Long getId () {
        return getRequestId();
    }
}
