package com.example.thesis.entity;

import com.example.thesis.entity.enums.ApproveDirection;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "comment")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher",referencedColumnName = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "document_id",referencedColumnName = "document_id")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "stage_id",referencedColumnName = "stage_id")
    private Stage stage;

    @Column(name = "from_type")
    @Enumerated(EnumType.STRING)
    private ApproveDirection fromType;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Override
    public Long getId () {
        return getCommentId();
    }
}
