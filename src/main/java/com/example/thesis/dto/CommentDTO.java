package com.example.thesis.dto;

import com.example.thesis.entity.enums.ApproveDirection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDTO {

    private String text;
    private ApproveDirection fromType;
    private LocalDateTime createdDate;
/*    private StudentRequestDTO studentDTO;
    private TeacherRequestDTO teacherDTO;
    private DocumentDTO documentDTO;*/

}
