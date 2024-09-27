package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public interface PostLogger {


    // Default method to log updates to a post
     default void logPostUpdate(Long postId, String updateType) {
         System.out.println("Post ID " + postId + " was " + updateType + " at " + java.time.LocalDateTime.now());
    }

}
