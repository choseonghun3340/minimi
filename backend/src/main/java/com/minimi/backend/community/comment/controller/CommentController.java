package com.minimi.backend.community.comment.controller;

import com.minimi.backend.community.comment.domain.CommentDTO;
import com.minimi.backend.community.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    //post comment
    @Operation(summary="댓글 생성", description="")
    @PostMapping("")
    public ResponseEntity postComment(@RequestBody CommentDTO request){

        commentService.createComment(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    //patch comment
    @Operation(summary="댓글 수정", description="")
    @PatchMapping("/{commentId}")
    public ResponseEntity patchComment(@PathVariable Long commentId,
                                       @RequestBody CommentDTO patch){
        commentService.patchComment(commentId, patch);
        return new ResponseEntity(HttpStatus.RESET_CONTENT);
    }
    //delete comment
    @Operation(summary="댓글 삭제", description="")
    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
