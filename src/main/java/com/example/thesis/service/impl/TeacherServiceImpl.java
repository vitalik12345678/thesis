package com.example.thesis.service.impl;

import com.example.thesis.entity.Teacher;
import com.example.thesis.exception.NotExistObjectException;
import com.example.thesis.repository.TeacherRepository;
import com.example.thesis.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl extends CRUDServiceImpl<Teacher,Long> implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    protected JpaRepository<Teacher, Long> getRepository () {
        return teacherRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher findByUserId (Long userId) {
        return teacherRepository.findByUserId(userId).orElseThrow( () -> new NotExistObjectException("Teacher with user id %s doesn't exist".formatted(userId)));
    }

    @Override
    public Optional<Teacher> findByUserIdOpt (Long userId) {
        return teacherRepository.findByUserId(userId);
    }

    @Override
    public void deleteByUserId(Long id) {
        teacherRepository.deleteByUserId(id);
    }
}
