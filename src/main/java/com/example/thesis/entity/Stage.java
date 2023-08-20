package com.example.thesis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Stage implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stage_id")
    private Long stageId;

    @Column(name = "name")
    private String name;

    @Column(name = "serial_order")
    private Integer serialOrder;

    @OneToMany(mappedBy = "stage")
    private List<Document> documentList;

    @Override
    public Long getId () {
        return getStageId();
    }
}
