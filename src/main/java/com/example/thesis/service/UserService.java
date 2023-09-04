package com.example.thesis.service;

import com.example.thesis.entity.User;

import java.util.Optional;

public interface UserService extends CRUDService<User,Long> {
    Optional<User> findByEmailOpt (String email);

    User saveStudent(User student);

    User saveTeacher(User teacher);
}
