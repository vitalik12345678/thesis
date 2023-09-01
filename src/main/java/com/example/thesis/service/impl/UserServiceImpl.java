package com.example.thesis.service.impl;

import com.example.thesis.entity.User;
import com.example.thesis.repository.UserRepository;
import com.example.thesis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CRUDServiceImpl<User,Long> implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected JpaRepository<User, Long> getRepository () {
        return userRepository;
    }
}
