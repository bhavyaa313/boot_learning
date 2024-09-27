package com.example.demo.service;

import com.example.demo.entity.Post;

@FunctionalInterface
public interface PostSorter {
    int compare(Post post1, Post post2);
}
