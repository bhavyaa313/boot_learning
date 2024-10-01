package com.example.demo.repository;

import com.example.demo.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p where ( p.author IS NULL OR p.author LIKE :author%)")
    List<Post> findByAuthor(@Param("author") String author);

    List<Post> findByTitle(String title);
}