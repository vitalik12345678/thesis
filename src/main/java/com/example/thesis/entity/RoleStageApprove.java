package com.example.thesis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table( name = "role_stage_approve"
        , uniqueConstraints =
                @UniqueConstraint(name = "role_stage_approve_unique_pair"
                        , columnNames = {"role_id","stage_id"}))
@Getter
@Setter
public class RoleStageApprove implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id",referencedColumnName = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "stage_id",referencedColumnName = "stage_id")
    private Stage stage;


}
