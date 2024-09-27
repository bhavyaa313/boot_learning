package com.example.demo.controller;

import com.example.demo.dto.PostRequestDto;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    public PostService postService;


    @PostMapping("/save")
    public ResponseEntity<PostResponseDto> saveOrUpdatePost(@Valid @RequestBody PostRequestDto postRequestDto) {
        PostResponseDto savedPost = postService.saveOrUpdatePost(postRequestDto);
        return ResponseEntity.status(savedPost.getId() == null ? HttpStatus.CREATED : HttpStatus.OK).body(savedPost);
    }

    @GetMapping("/all-posts")
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
           return postService.deletePost(id);
    }

    @GetMapping("/get-post-by-id/{id}")
    public Optional<PostResponseDto> getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/get-post-by-author")
    public List<PostResponseDto> getPostByAuthor(@RequestParam(required = false) String author) {
        return postService.getPostByAuthor(author);
    }

    @GetMapping("/get-post-by-title")
    public List<PostResponseDto> getPostByTitle(@RequestParam(required = false) String title) {
        return postService.getPostByTitle(title);
    }

    @GetMapping("/get-sorted-post-by-date")
    public List<PostResponseDto> getSortedPostByDate() {
        return postService.sortPostsByDate();
    }

    @GetMapping("/get-sorted-post-by-title")
    public List<PostResponseDto> getSortedPostByTitle() {
        return postService.sortPostsByTitle();
    }



}


