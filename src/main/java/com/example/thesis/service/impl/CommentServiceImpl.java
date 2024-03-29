package com.example.thesis.service.impl;

import com.example.thesis.entity.Comment;
import com.example.thesis.facade.UserFacade;
import com.example.thesis.repository.CommentRepository;
import com.example.thesis.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends CRUDServiceImpl<Comment,Long> implements CommentService {

    private final CommentRepository commentRepository;
    private final DocumentService documentService;
    private final UserService userService;

    @Override
    protected JpaRepository<Comment, Long> getRepository () {
        return commentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAllByDocumentId (Long documentId) {
        return commentRepository.findAllByDocumentId(documentId);
    }

    @Override
    @Transactional
    public Comment add (Comment comment, Long documentId, Long userId) {
        comment.setCreatedDate(LocalDateTime.now());
        comment = this.save(comment);
        comment.setDocument(documentService.findById(documentId));
        comment.setUser(userService.findById(userId));
        return comment;
    }


}
