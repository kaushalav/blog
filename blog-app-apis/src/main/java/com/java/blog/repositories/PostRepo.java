package com.java.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.blog.entities.Category;
import com.java.blog.entities.Post;
import com.java.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findByPostTitleContaining(String title);

    // example of querry
    @Query("select p from Post p where p.postContent like :key")
    List<Post> findByContent(@Param("key") String key);
}
