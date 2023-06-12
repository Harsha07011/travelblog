package com.myblog.service;

import com.myblog.payload.PostDTO;
import com.myblog.payload.PostResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);


    PostDTO updatePost(PostDTO postDTO,long id);
    void deletePostById(long id);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDTO getPostById(long id);
}
