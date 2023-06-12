package com.myblog.service;

import com.myblog.payload.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(long postId, CommentDTO commentDTO);
    List<CommentDTO> getCommentsByPostId(long postId);
    CommentDTO getCommentById(Long postId, Long commentId);
    CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDto);
    void deleteComment(Long postId, Long commentId);

}
