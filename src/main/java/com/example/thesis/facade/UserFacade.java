package com.example.thesis.facade;

import com.example.thesis.dto.*;
import com.example.thesis.entity.User;
import com.example.thesis.exception.NullObjectException;
import com.example.thesis.exception.ValidationException;
import com.example.thesis.factory.UserFactory;
import com.example.thesis.security.UserPrincipal;
import com.example.thesis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final UserFactory userFactory;
    private final RoleFacade roleFacade;
    private final TeacherFacade teacherFacade;
    private final StudentFacade studentFacade;
    private final TokenFacade tokenFacade;

    @Transactional
    public void saveTeacher (TeacherRegistrationDTO teacherRegistrationDTO) {
        var user = userFactory.fromRegistrationDTO(teacherRegistrationDTO);
        user = userService.saveTeacher(user);
        teacherFacade.create(teacherRegistrationDTO, user);
    }
    @Transactional
    public void saveTeacher (TeacherRegistrationHODDTO teacherRegistrationHODDTO) {
        var user = userFactory.fromRegistrationDTO(teacherRegistrationHODDTO);
        user = userService.saveTeacher(user);
        tokenFacade.deleteByToken(teacherRegistrationHODDTO.getToken());
        teacherFacade.create(teacherRegistrationHODDTO, user);
    }

    @Transactional
    public void saveStudent (StudentRegistrationDTO studentRegistrationDTO) {
        var user = userFactory.fromRegistrationDTO(studentRegistrationDTO);
        user = userService.saveStudent(user);
        tokenFacade.deleteByToken(studentRegistrationDTO.getToken());
        studentFacade.create(studentRegistrationDTO,user);
    }

    @Transactional(readOnly = true)
    public FullUserInfoDTO getCurrentUser () {
        var userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userPrincipal.getUser();
        if (Objects.isNull(user)) {
            throw new NullObjectException("User is null");
        }
        var fullUserInfoDTO = userFactory.toFullUserInfoDTO(user);

        if (user.getRole().getName().equals("student")) {
            var currentStudentDTO = studentFacade.getCurrentStudentDTOByUserId(user.getUserId());
            fullUserInfoDTO.setStudentDTO(currentStudentDTO);
        } else {
            var currentTeacherDTO = teacherFacade.getCurrentTeacherDTOByUserId(user.getUserId());
            fullUserInfoDTO.setTeacherDTO(currentTeacherDTO);
        }

        return fullUserInfoDTO;
    }

    @Transactional(readOnly = true)
    public FullUserInfoDTO findById(Long id) {
        var fullUserInfoDTO = userFactory.toFullUserInfoDTO(userService.findById(id));

        switch (fullUserInfoDTO.getRoleDTO().getName()) {
            case "teacher" -> {
                var currentTeacherDTO = teacherFacade.getCurrentTeacherDTOByUserId(fullUserInfoDTO.getUserId());
                fullUserInfoDTO.setTeacherDTO(currentTeacherDTO);
            }
            case "student" -> {
                var currentStudentDTO = studentFacade.getCurrentStudentDTOByUserId(fullUserInfoDTO.getUserId());
                fullUserInfoDTO.setStudentDTO(currentStudentDTO);
            }
        }

        return fullUserInfoDTO;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return userFactory.toUserDTOList(userService.findAll());
    }

    @Transactional
    public void deleteById(Long id) {
        studentFacade.deleteByUserId(id);
        teacherFacade.deleteByUserId(id);
        userService.delete(id);
    }

    @Transactional
    public void updateHodStudent(StudentUpdateHodDTO dto, Long id) {

        User user = userService.findById(id);
        BeanUtils.copyProperties(dto,user);
        var role = roleFacade.findByIdEntity(dto.getRoleId());
        if (role.getName().equals("teacher") || role.getName().equals("HoD") || role.getName().equals("PC")) {
            throw new ValidationException("Student cannot be teacher ");
        }
        userService.update(user);
        user.setRole(role);

        studentFacade.updateHodStudentByUserId(dto,id);

    }

    @Transactional
    public void updateHodTeacher(TeacherUpdateHodDTO dto, Long id) {

        User user = userService.findById(id);
        BeanUtils.copyProperties(dto,user);
        var role = roleFacade.findByIdEntity(dto.getRoleId());
        if (role.getName().equals("student")) {
            throw new ValidationException("Teacher cannot be student ");
        }
        user.setRole(role);
        userService.update(user);

        teacherFacade.updateHodTeacherByUserid(dto,id);
    }
}
