package com.springboot.linkedin.posts_service.controller;

import com.springboot.linkedin.posts_service.dto.PostCreateRequestDTO;
import com.springboot.linkedin.posts_service.dto.PostDTO;
import com.springboot.linkedin.posts_service.entity.Post;
import com.springboot.linkedin.posts_service.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostCreateRequestDTO postDTO, HttpServletRequest httpServletRequest){
        PostDTO createdPost = postService.createPost(postDTO, 1L);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long postId){
        PostDTO postDTO = postService.getPostById(postId);
        return ResponseEntity.ok(postDTO);
    }
}
