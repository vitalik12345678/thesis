package com.example.thesis.entity;

import com.example.thesis.entity.enums.ApproveStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Document implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;

    @Override
    public Long getId () {
        return getDocumentId();
    }

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "content_id")
    @ContentId
    private String contentId;

    @Column(name = "approved",columnDefinition = "boolean default false")
    private Boolean approved;

    @Column(name = "length")
    @ContentLength
    private String length;

    @Column(name = "approve_status")
    @Enumerated(EnumType.STRING)
    private ApproveStatus status;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "approved_date")
    private LocalDateTime approvedDate;

    @ManyToOne()
    @JoinColumn(name = "stage",referencedColumnName = "stage_id")
    private Stage stage;

    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "student_id")
    private Student student;
}
