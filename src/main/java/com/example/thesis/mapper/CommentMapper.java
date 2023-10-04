package com.example.thesis.mapper;

import com.example.thesis.dto.CommentCreateDTO;
import com.example.thesis.dto.CommentDTO;
import com.example.thesis.entity.Comment;

import java.util.List;

public interface CommentMapper {
    CommentDTO toCommentDTO (Comment comment);

    List<CommentDTO> toCommentDTOList (List<Comment> commentList);

    Comment toComment (CommentCreateDTO commentCreateDTO);

}
