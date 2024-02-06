package com.example.thesis.service.impl;

import com.example.thesis.entity.User;
import com.example.thesis.exception.ValidationException;
import com.example.thesis.repository.UserRepository;
import com.example.thesis.service.RoleService;
import com.example.thesis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDServiceImpl<User,Long> implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected JpaRepository<User, Long> getRepository () {
        return userRepository;
    }

    @Override
    @Transactional
    public User save (User entity) {
        if (findByEmailOpt(entity.getEmail()).isPresent() ) {
            throw new ValidationException("Email already in use");
        }
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        return super.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmailOpt (String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User saveStudent (User student) {
        student.setRole(roleService.getReferenceById(roleService.findByName("student").getId()));
        return save(student);
    }

    @Override
    @Transactional
    public User saveTeacher (User teacher) {
        teacher.setRole(roleService.findByName("teacher"));
        return save(teacher);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findByEmailList(List<String> emails) {
        return userRepository.findAllByEmailIn(emails);
    }
}
