package com.example.demo.serviceImpl;

import com.example.demo.model.dto.request.PostRequestDto;
import com.example.demo.model.dto.response.PostResponseDto;
import com.example.demo.model.entity.Post;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.helper.PostHelper;
import com.example.demo.model.mapper.PostMapper;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostLogger;
import com.example.demo.service.PostService;
import com.example.demo.service.PostSorter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class PostServiceImpl implements PostService, PostLogger {

    @Autowired
    private PostRepository postRepository;

    Logger log = LogManager.getLogger(PostServiceImpl.class);


    @Autowired
    private PostMapper postMapper;


    @Override
    public PostResponseDto saveOrUpdatePost(PostRequestDto postRequestDto) {
        // If ID is null or doesn't exist, it's a new post (create operation)
        var id = postRequestDto.getId();
        if (id == null || id == 0) {
            Post post = postMapper.postDtoToPost(postRequestDto);
            post.setCreatedDate(LocalDateTime.now());
            StringJoiner stringJoiner = new StringJoiner("| ");
            stringJoiner.add("Title: " + post.getTitle());
            stringJoiner.add("Author: " + post.getAuthor());
            stringJoiner.add("Content: " + post.getContent());
            stringJoiner.add("Date: " + post.getCreatedDate());
            log.info("Creating new post: {}", stringJoiner);
            Post savedPost = postRepository.save(post);  // Save the post
            return postMapper.postToPostResponseDto(savedPost);
        } else {
            Optional<Post> postOptional = postRepository.findById(id);
            if (postOptional.isPresent()) {
                Post existingPost = postOptional.get();
                existingPost.setModifiedDate(LocalDateTime.now());
                log.info("Updating post", existingPost);
                Post updatedPost = postRepository.save(existingPost);
                return postMapper.postToPostResponseDto(updatedPost);

            } else {
                throw new PostNotFoundException("Post with ID " + id + " not found");
            }
        }
    }


    @Override
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::postToPostResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Void> deletePost(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            postRepository.deleteById(id);
        } else {
            throw new PostNotFoundException("Post with ID " + id + " not found");
        }

        return null;
    }

    @Override
    public Optional<PostResponseDto> getPostById(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            PostResponseDto postResponseDto = postMapper.postToPostResponseDto(postOptional.get());
            return Optional.of(postResponseDto);
        } else {
            throw new PostNotFoundException("Post with ID " + id + " not found");
        }
    }


    @Override
    public List<PostResponseDto> getPostByAuthor(String author) {
        List<Post> posts = postRepository.findAll();
        //      return posts.stream().filter(post -> author == null || post.getAuthor().toLowerCase().startsWith(author.toLowerCase())).collect(Collectors.toList());
        PostHelper.setAuthor(author);

        return posts.stream()
                .filter(PostHelper::isAuthorMatching).map(postMapper::postToPostResponseDto)  // use of Method reference, passing author from context
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDto> getPostByTitle(String title) {
        List<Post> posts = postRepository.findAll();
        // use of collectors, lambda, stream api
        return posts.stream().filter(post -> title == null || stream(post.getTitle().toLowerCase().split("\\s+")).anyMatch(word -> word.startsWith(title.toLowerCase()))).map(postMapper::postToPostResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDto> sortPostsByDate() {
        List<Post> posts = postRepository.findAll();  // Fetch all Posts
        posts.sort(Comparator.comparing(Post::getCreatedDate));
        return posts.stream()
                .map(postMapper::postToPostResponseDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<PostResponseDto> sortPosts(PostSorter sorter) {
        List<Post> posts = postRepository.findAll();
        // Use method reference from the functional interface
        //his method reference is equivalent to passing a lambda expression like (post1, post2) -> sorter.compare(post1, post2).
        return posts.stream()
                .sorted(sorter::compare).map(postMapper::postToPostResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDto> sortPostsByTitle() {
        List<Post> posts = postRepository.findAll();
        // Use PostHelper's method as a method reference
        //PostHelper::sortByTitle is a method reference to the static method sortByTitle in PostHelper.
        //It matches the compare method in the PostSorter interface signature, so it can be used wherever a PostSorter is expected.
        return sortPosts(PostHelper::sortByTitle);
    }

    @Override
    public void logPostUpdate(Long postId, String updateType) {
        PostService.super.logPostUpdate(postId, updateType);
    }


}


