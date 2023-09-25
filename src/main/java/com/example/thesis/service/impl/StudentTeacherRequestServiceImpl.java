package com.example.thesis.service.impl;

import com.example.thesis.dto.TeacherStudentRequestCreateDTO;
import com.example.thesis.entity.Student;
import com.example.thesis.entity.Teacher;
import com.example.thesis.entity.TeacherStudentRequest;
import com.example.thesis.repository.StudentTeacherRequestRepository;
import com.example.thesis.service.StudentService;
import com.example.thesis.service.StudentTeacherRequestService;
import com.example.thesis.service.TeacherService;
import com.example.thesis.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentTeacherRequestServiceImpl extends CRUDServiceImpl<TeacherStudentRequest,Long> implements StudentTeacherRequestService {

    private final StudentTeacherRequestRepository repository;
    private final StudentService studentService;
    private final ThemeService themeService;
    private final TeacherService teacherService;


    @Override
    protected JpaRepository<TeacherStudentRequest, Long> getRepository () {
        return repository;
    }

    @Override
    @Transactional
    public TeacherStudentRequest add (Long studentId, Long teacherId, TeacherStudentRequestCreateDTO dto) {

        var student = studentService.findById(studentId);
        var teacher = teacherService.findById(teacherId);

        if (findByTeacherAndStudentIdOpt(teacherId, studentId).isPresent()) {
            throw new RuntimeException("Request exits");
        }

        var request = new TeacherStudentRequest();
        request.setStudent(student);
        request.setTeacher(teacher);
        request.setTheme(dto.getTheme());
        request.setLanguage(dto.getLanguage());
        request.setApproved(false);
        request.setCreatedDate(LocalDateTime.now());

        return save(request);
    }

    @Override
    @Transactional(readOnly = true)
    public TeacherStudentRequest findByTeacherAndStudentId (Long teacherId, Long studentId) {
        return repository.findByTeacherIdAndStudentId(teacherId,studentId).orElseThrow( () -> new RuntimeException("Reqeust doenst' exist") );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TeacherStudentRequest> findByTeacherAndStudentIdOpt (Long teacherId, Long studentId) {
        return repository.findByTeacherIdAndStudentId(teacherId,studentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherStudentRequest> findByTeacher (Teacher teacher) {
        return repository.findAllByTeacher(teacher);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherStudentRequest> findByStudent (Student student) {
        return repository.findAllByStudent(student);
    }

    @Override
    public TeacherStudentRequest approve (TeacherStudentRequest request) {
        var student = studentService.findById(request.getStudent().getStudentId());
        if (Objects.nonNull(student.getAdviser())) {
            throw new RuntimeException("Student has adviser");
        }
        var teacher = teacherService.findById(request.getTeacher().getTeacherId());
        var studentList = findByTeacher(teacher).stream().filter(TeacherStudentRequest::getApproved)
                .filter(item -> item.getStudent().getDegree().equals(student.getDegree())).toList();

        switch (student.getDegree()) {
            case MASTER -> {
                if (Objects.isNull(teacher.getGeneralMaster())) {
                    throw new RuntimeException("Teacher cannot have any masters' students");
                }
                if (studentList.size() >= teacher.getGeneralMaster()) {
                    throw new RuntimeException("Teacher overloaded by masters");
                }
            }
            case BACHELOR -> {
                if (Objects.isNull(teacher.getGeneralBachelor())) {
                    throw new RuntimeException("Teacher cannot have any bachelors' students");
                }
                if (studentList.size() >= teacher.getGeneralBachelor()) {
                    throw new RuntimeException("Teacher overloaded by bachelors");
                }
            }
        }
        var theme = themeService.createTheme(request.getLanguage(),request.getTheme(),request.getStudent());
        student.setAdviser(teacher);
        student.setTheme(theme);
        repository.save(request);

        return request;
    }
}
