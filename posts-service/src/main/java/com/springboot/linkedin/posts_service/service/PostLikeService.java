package com.springboot.linkedin.posts_service.service;

import com.springboot.linkedin.posts_service.entity.PostLike;
import com.springboot.linkedin.posts_service.exception.BadRequestException;
import com.springboot.linkedin.posts_service.exception.ResourceNotFoundException;
import com.springboot.linkedin.posts_service.repository.PostLikeRepository;
import com.springboot.linkedin.posts_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public void likePost(Long postId, Long userId){

        log.info("Attempting to like the post with id: {}", postId);

        boolean exists = postRepository.existsById(postId);
        if(!exists){
            throw new ResourceNotFoundException("Post does not exists with id: " + postId);
        }

        boolean alreadyLiked = postLikeRepository.existsByPostIdAndUserId(postId, userId);
        if(alreadyLiked){
            throw new BadRequestException("Cannot like the same post again.");
        }

        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);

        log.info("Post with id: {} liked successfully", postId);

    }
}
