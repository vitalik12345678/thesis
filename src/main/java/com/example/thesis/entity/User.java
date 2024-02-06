package com.example.thesis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends Contributor implements EntityWithId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne( cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id",referencedColumnName = "role_id")
    private Role role;

    @Override
    public Long getId () {
        return getUserId();
    }
}
