package com.java.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.blog.entities.Category;
import com.java.blog.entities.Post;
import com.java.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);
}
