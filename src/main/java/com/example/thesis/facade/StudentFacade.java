package com.example.thesis.facade;

import com.example.thesis.dto.*;
import com.example.thesis.entity.Document;
import com.example.thesis.entity.Student;
import com.example.thesis.entity.User;
import com.example.thesis.entity.enums.ApproveDirection;
import com.example.thesis.factory.StudentFactory;
import com.example.thesis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StudentFacade {

    private final StageFacade stageFacade;
    private final StudentFactory studentFactory;
    private final StudentService studentService;
    private final DocumentFacade documentFacade;
    private final StudentTeacherRequestFacade requestFacade;

    public void create(StudentRegistrationDTO studentRegistrationDTO, User user) {
        var student = studentFactory.fromStudentRegistrationDTO(studentRegistrationDTO);
        student.setUser(user);
        studentService.save(student);
    }

    @Transactional(readOnly = true)
    public List<StudentFileInfoDTO> findFileInfoListById(Long studentId) {
        return studentFactory.toStudentFileInfoDTOList(documentFacade.findDocumentListByStudentId(studentId));
    }

    @Transactional
    public StudentTeacherRequestProfileDTO createStudentTeacherRequest(Long studentId, Long teacherId, TeacherStudentRequestCreateDTO createDTO) {
        createDTO.setApproveDirection(ApproveDirection.TEACHER);
        return requestFacade.createRequest(studentId, teacherId, createDTO);
    }

    @Transactional(readOnly = true)
    public StudentDTO getCurrentStudentDTOByUserId(Long userId) {
        var student = studentService.findByUserId(userId);
        return studentFactory.toStudentDTO(student);
    }

    @Transactional(readOnly = true)
    public List<StudentRequestDTO> findAll() {
        var studentList = studentService.findAll();
        return studentFactory.toStudentRequestDTOList(studentList);
    }

    @Transactional(readOnly = true)
    public List<StudentRequestFromTeacherDTO> findStudentRequestByStudent(Student student) {
        return requestFacade.findStudentRequestList(student);
    }

    @Transactional(readOnly = true)
    public CurrentAdviserDTO findCurrentAdviser(Student student) {
        var existStudent = studentService.findById(student.getStudentId());
        var requestList = requestFacade.findStudentRequestList(existStudent);
        var request = requestList.stream().filter(StudentRequestFromTeacherDTO::getHeadApprove).findFirst();
        var adviserDTO = studentFactory.toCurrentAdviserDTO(existStudent);
        var lastDocument = existStudent.getDocumentList().stream()
                .max(Comparator.comparing(Document::getCreatedDate).thenComparingInt(a -> a.getStage().getSerialOrder()));
        lastDocument.ifPresentOrElse(
                document -> {
                    var stageDTO = documentFacade.findStageDTOByDocumentId(document.getDocumentId());
                    adviserDTO.setStageDTO(stageDTO);
                },
                () -> {
                    var stageDTO = stageFacade.getStageDTOList().stream().min(Comparator.comparing(StageDTO::getSerialOrder));
                    if (stageDTO.isEmpty()) throw new RuntimeException("No stage in the system.");
                    else adviserDTO.setStageDTO(stageDTO.get());
                }
        );
        request.ifPresentOrElse(
                item -> adviserDTO.setHeadApprove(item.getHeadApprove()),
                () -> adviserDTO.setHeadApprove(false)
        );
        return adviserDTO;
    }

    @Transactional
    public void deleteByUserId(Long id) {
        studentService.deleteByUserId(id);
    }

    @Transactional
    public void updateHodStudentByUserId(StudentUpdateHodDTO dto, Long id) {
        Student student = studentService.findByUserId(id);
        studentFactory.copyToStudent(dto, student);
        studentService.save(student);

    }

    @Transactional(readOnly = true)
    public List<DocumentDTO> getHodInfoList() {

        var students = studentService.findAll();
        var lastDocument = students.stream()
                .map(
                        item -> item.getDocumentList().stream()
                                .max(Comparator.comparing((Document a) -> a.getStage().getSerialOrder())
                                        .thenComparing(Document::getCreatedDate)))
                .filter(Optional::isPresent).map(item -> studentFactory.toHodInfoDto(item.get())).toList();

        return lastDocument;
    }
}
