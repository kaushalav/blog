package com.java.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    
}
