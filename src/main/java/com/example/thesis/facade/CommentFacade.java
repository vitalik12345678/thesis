package com.example.thesis.facade;

import com.example.thesis.dto.CommentCreateDTO;
import com.example.thesis.dto.CommentDTO;
import com.example.thesis.dto.DocumentDTO;
import com.example.thesis.entity.Comment;
import com.example.thesis.factory.CommentFactory;
import com.example.thesis.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentFacade {

    private final CommentService commentService;
    private final CommentFactory commentFactory;

    @Transactional(readOnly = true)
    public List<CommentDTO> findAllCommentListByDocumentId (Long documentId) {
        var commentList = commentService.findAllByDocumentId(documentId);
        commentList.sort(Comparator.comparing(Comment::getCreatedDate));
        return commentFactory.toCommentDTOList(commentList);
    }

    @Transactional
    public CommentDTO create (CommentCreateDTO commentCreateDTO, Long documentId, Long teacherId, Long studentId) {

        var comment = commentFactory.toComment(commentCreateDTO);

        return commentFactory.toCommentDTO(commentService.add(comment,documentId,teacherId,studentId));

    }

    @Transactional
    public CommentDTO delete (Long commentId) {
        return commentFactory.toCommentDTO(commentService.delete(commentId));
    }
}
