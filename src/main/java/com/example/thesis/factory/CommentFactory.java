package com.example.thesis.factory;

import com.example.thesis.dto.CommentCreateDTO;
import com.example.thesis.dto.CommentDTO;
import com.example.thesis.entity.Comment;
import com.example.thesis.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentFactory {

    private final EntityMapper entityMapper;

    public List<CommentDTO> toCommentDTOList (List<Comment> commentList) {
        return entityMapper.toCommentDTOList(commentList);
    }

    public CommentDTO toCommentDTO (Comment comment) {
        return entityMapper.toCommentDTO(comment);
    }

    public Comment toComment (CommentCreateDTO commentCreateDTO) {
        return entityMapper.toComment(commentCreateDTO);
    }
}
