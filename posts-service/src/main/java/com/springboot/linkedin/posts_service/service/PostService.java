package com.springboot.linkedin.posts_service.service;

import com.springboot.linkedin.posts_service.dto.PostCreateRequestDTO;
import com.springboot.linkedin.posts_service.dto.PostDTO;
import com.springboot.linkedin.posts_service.entity.Post;
import com.springboot.linkedin.posts_service.event.PostCreatedEvent;
import com.springboot.linkedin.posts_service.exception.ResourceNotFoundException;
import com.springboot.linkedin.posts_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    private final KafkaTemplate<Long, PostCreatedEvent> kafkaTemplate;

    public PostDTO createPost(PostCreateRequestDTO postDTO, Long userId) {
        Post post = modelMapper.map(postDTO, Post.class);
        post.setUserId(userId);

        Post savedPost = postRepository.save(post);

        PostCreatedEvent postCreatedEvent = PostCreatedEvent.builder()
                .postId(savedPost.getId())
                .creatorId(userId)
                .content(savedPost.getContent())
                .build();

        kafkaTemplate.send("post-created-event", postCreatedEvent);

        return modelMapper.map(savedPost, PostDTO.class);
    }

    public PostDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        return modelMapper.map(post, PostDTO.class);
    }

    public List<PostDTO> getAllPostsOfUser(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);

        return posts.stream()
                .map((element) -> modelMapper.map(element, PostDTO.class))
                .toList();
    }
}
