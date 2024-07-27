package com.java.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.blog.payloads.ApiResponse;
import com.java.blog.payloads.PostDto;
import com.java.blog.services.PostService;


@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
            @PathVariable Integer categoryId) {
        PostDto createdPostDto = postService.createPost(postDto, categoryId, userId);
        return new ResponseEntity<PostDto>(createdPostDto, HttpStatus.CREATED);
    }
    
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPost(
        @RequestParam(value = "pageNumber", defaultValue = "0", required = false)Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "5",required = false) Integer pageSize
    ) 
    {
        List<PostDto> postDtoList = postService.getAllPost(pageNumber,pageSize);
        return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto postDto = postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.FOUND);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getByUser(@PathVariable Integer userId) {
        List<PostDto> postDtoList = postService.getAllPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getByCategory(@PathVariable Integer categoryId) {
        List<PostDto> postDtoList = postService.getAllPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post is deleted", true), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatedPostDto = postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK);
    }


}
