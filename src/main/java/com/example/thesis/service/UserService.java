package com.example.thesis.service;

import com.example.thesis.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends CRUDService<User,Long> {
    Optional<User> findByEmailAndNotUserId(String email, Long userId);

    Optional<User> findByEmailOpt (String email);

    User saveStudent(User student);

    User saveTeacher(User teacher);

    List<User> findByEmailList(List<String> emails);
}
