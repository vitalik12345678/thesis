package com.example.thesis.mapper;

import com.example.thesis.dto.CommentCreateDTO;
import com.example.thesis.dto.CommentDTO;
import com.example.thesis.entity.Comment;
import org.mapstruct.Mapping;

import java.util.List;

public interface CommentMapper {

    @Mapping(source = "user.userId",target = "userId")
    CommentDTO toCommentDTO (Comment comment);

    List<CommentDTO> toCommentDTOList (List<Comment> commentList);

    Comment toComment (CommentCreateDTO commentCreateDTO);

}
