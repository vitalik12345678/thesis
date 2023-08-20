package com.example.thesis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "name")
    private String name;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "link")
    private String link;

    @Column(name = "extension")
    private String extension;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage",referencedColumnName = "stage_id")
    private Stage stage;
}
