package com.myblog.controller;

import com.myblog.payload.CommentDTO;
import com.myblog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //http://localhost:8080/api/posts/1/comments
   @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO>createComment(
            @PathVariable(value = "postId") long postId,
            @RequestBody CommentDTO commentDTO){
        return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
   }

    //http://localhost:8080/api/posts/1/comments
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId(@PathVariable(value = "postId")
                                                Long postId){
        return commentService.getCommentsByPostId(postId);
    }
    //http://localhost:8080/api/posts/1/comments/1
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "id") Long commentId){
        CommentDTO commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }
    //http://localhost:8080/api/posts/1/comments/1
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO>updateComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "id") Long commentId,
            @RequestBody CommentDTO commentDto){
        CommentDTO updatedComment=commentService.updateComment(postId,commentId,commentDto);
        return new ResponseEntity<>(updatedComment,HttpStatus.OK);
    }
    //http://localhost:8080/api/posts/1/comments/1
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String>deleteComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "id") Long commentId){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment is deleted successFully",HttpStatus.OK);
    }

}
