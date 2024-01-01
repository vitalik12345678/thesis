package com.example.thesis.service;

import com.example.thesis.dto.CommentCreateDTO;
import com.example.thesis.entity.Comment;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CommentService extends CRUDService<Comment,Long> {
    List<Comment> findAllByDocumentId (Long documentId);

    Comment add (Comment comment, Long documentId, Long teacherId, Long studentId,Long stageId);
}
