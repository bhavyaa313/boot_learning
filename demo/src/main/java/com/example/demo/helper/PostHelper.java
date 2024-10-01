package com.example.demo.helper;

import com.example.demo.model.entity.Post;
import lombok.Setter;


public class PostHelper{
    //The static field allowed you to access author inside the method without passing it as a parameter.
    @Setter
    private static String author; // Static field to hold author

    public static boolean isAuthorMatching(Post post) {
        return author == null || post.getAuthor().toLowerCase().startsWith(author.toLowerCase());
    }


        // Static method to compare posts by title
        public static int sortByTitle(Post post1, Post post2) {
            return post1.getTitle().compareToIgnoreCase(post2.getTitle());
        }


}
