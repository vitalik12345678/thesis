package com.example.thesis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;

import java.util.List;

@Entity
@Table
@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage",referencedColumnName = "stage_id")
    private Stage stage;

    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "student_id")
    private Student student;
}
