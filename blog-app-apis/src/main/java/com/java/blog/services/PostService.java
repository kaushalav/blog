package com.java.blog.services;

import java.util.List;

import com.java.blog.payloads.PostDto;
import com.java.blog.payloads.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer postCategoryId, Integer postUserId);

    void deletePost(Integer postId);

    PostDto updatePost(PostDto postDto, Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);

    PostDto getPostById(Integer postId);

    List<PostDto> getAllPostsByCategory(Integer categoryId);

    List<PostDto> getAllPostsByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);

    List<PostDto> searchPostByContent(String keyword);
}
