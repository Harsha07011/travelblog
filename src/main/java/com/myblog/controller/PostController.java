package com.myblog.controller;

import com.myblog.payload.PostDTO;
import com.myblog.payload.PostResponse;
import com.myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8080/api/posts
    @PostMapping
   @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?>createPost(@Valid @RequestBody PostDTO postDto, BindingResult result){

        if (result.hasErrors()){
            return  new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        PostDTO dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/posts
    //http://localhost:8080/api/posts?pageNo=0&pageSize=10
    //http://localhost:8080/api/posts?pageNo=0&pageSize=10&sortBy=title&sortDir=asc
    //change the method name postResponse
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }
    //http://localhost:8080/api/posts/1
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") long id) {
        PostDTO post = postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    //http://localhpost:8080/api/posts/1
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable("id") long id) {
        PostDTO updatedPost = postService.updatePost(postDTO, id);
        return  ResponseEntity.ok(updatedPost);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") long id) {
       postService.deletePostById(id);
       return new ResponseEntity<String>("post is deleted " , HttpStatus.OK);
    }
}
