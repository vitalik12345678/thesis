package com.example.thesis.conroller;

import com.example.thesis.dto.CommentCreateDTO;
import com.example.thesis.dto.CommentDTO;
import com.example.thesis.facade.CommentFacade;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentFacade commentFacade;

    @GetMapping(value = "/{documentId}")
    @PermitAll()
    public ResponseEntity<List<CommentDTO>> getCommentDTOListByDocumentId (@PathVariable Long documentId) {
        return ResponseEntity.ok(commentFacade.findAllCommentListByDocumentId(documentId));
    }

    @PostMapping(value = "/{documentId}/{userId}")
    @PermitAll()
    public ResponseEntity<?> createComment(@RequestBody CommentCreateDTO commentCreateDTO,
                                           @PathVariable Long documentId,
                                           @PathVariable Long userId) {
        commentFacade.create(commentCreateDTO,documentId,userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentFacade.delete(commentId));
    }
}
