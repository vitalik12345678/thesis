package com.example.thesis.facade;

import com.example.thesis.dto.*;
import com.example.thesis.entity.Document;
import com.example.thesis.entity.Teacher;
import com.example.thesis.entity.TeacherStudentRequest;
import com.example.thesis.entity.User;
import com.example.thesis.entity.enums.ApproveDirection;
import com.example.thesis.factory.TeacherFactory;
import com.example.thesis.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeacherFacade {

    private final TeacherService teacherService;
    private final TeacherFactory teacherFactory;
    private final StudentTeacherRequestFacade requestFacade;
    private final TeacherApproveFacade teacherApproveFacade;

    @Transactional
    public void create(TeacherRegistrationDTO teacherRegistrationDTO, User user) {
        var teacher = teacherFactory.fromTeacherRegistrationDTO(teacherRegistrationDTO);
        teacher.setUser(user);
        teacherService.save(teacher);
    }

    @Transactional(readOnly = true)
    public CurrentTeacherDTO getCurrentTeacherDTOByUserId(Long userId) {
        var teacher = teacherService.findByUserId(userId);
        var dto = teacherFactory.toCurrentTeacherDTO(teacher);
        dto.setAvailableStageIdSet(teacherApproveFacade.findAvailableStageIdsByTeacherId(teacher.getTeacherId()));
        return dto;
    }

    @Transactional(readOnly = true)
    public List<TeacherRequestDTO> findAllTeacher() {
        var teacherList = teacherService.findAll();
        return teacherFactory.toTeacherRequestDTOList(teacherList);
    }

    @Transactional
    public StudentTeacherRequestProfileDTO createStudentTeacherRequest(Long teacherId, Long studentId, TeacherStudentRequestCreateDTO createDTO) {
        createDTO.setApproveDirection(ApproveDirection.STUDENT);
        return requestFacade.createRequest(studentId, teacherId, createDTO);
    }

    @Transactional(readOnly = true)
    public List<TeacherRequestFromStudentDTO> getTeacherRequestList(Teacher teacher) {
        return (requestFacade.findTeacherRequestList(teacher));
    }

    @Transactional(readOnly = true)
    public List<CurrentAdviserStudentDTO> findCurrentStudentList(Teacher teacher) {
        var existTeacher = teacherService.findById(teacher.getTeacherId());
        var studentList = existTeacher.getRequestList()
                .stream()
                .filter(TeacherStudentRequest::getApproved)
                .toList();
        //TODO add field HoD approve
        var currentStudentDTOList = teacherFactory.toCurrentAdviserStudentDTOList(studentList);
        Map<Long, Optional<Document>> studentStageDTOMap = studentList.stream().collect(Collectors.toMap(
                key -> key.getStudent().getStudentId(),
                value -> value.getStudent().getDocumentList().
                        stream().max(Comparator.comparing(Document::getCreatedDate))

        ));
        currentStudentDTOList.forEach(currentAdviserStudentDTO -> {
            var document = studentStageDTOMap.get(currentAdviserStudentDTO.getStudentRequestDTO().getStudentId());
            document.ifPresent(existDocument -> currentAdviserStudentDTO.setStageDTO(teacherFactory.toStageDTO(existDocument.getStage())));
        });
        return currentStudentDTOList;
    }

    @Transactional
    public void deleteByUserId(Long id) {
        teacherService.deleteByUserId(id);
    }
}
