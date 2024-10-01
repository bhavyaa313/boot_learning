package com.example.demo.service;

import com.example.demo.model.dto.request.PostRequestDto;
import com.example.demo.model.dto.response.PostResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface PostService {


    List<PostResponseDto> getAllPosts();
    ResponseEntity<Void> deletePost(Long id);
    Optional<PostResponseDto> getPostById(Long id);
    PostResponseDto saveOrUpdatePost(PostRequestDto post);
    List<PostResponseDto> getPostByAuthor(String Author);
    List<PostResponseDto> getPostByTitle(String title);
    List<PostResponseDto> sortPostsByDate();
    List<PostResponseDto> sortPosts(PostSorter sorter);
    List<PostResponseDto> sortPostsByTitle();

    default void logPostUpdate(Long postId, String updateType) {
        System.out.println("Post ID -------- " + postId + " was " + updateType + " at " + java.time.LocalDateTime.now());
    }

}
