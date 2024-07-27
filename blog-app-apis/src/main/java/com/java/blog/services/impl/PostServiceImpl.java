package com.java.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.java.blog.entities.Post;
import com.java.blog.entities.Category;
import com.java.blog.entities.User;
import com.java.blog.exception.ResourceNotFoundException;
import com.java.blog.payloads.PostDto;
import com.java.blog.payloads.PostResponse;
import com.java.blog.repositories.CategoryRepo;
import com.java.blog.repositories.PostRepo;
import com.java.blog.repositories.UserRepo;
import com.java.blog.services.PostService;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;
    
    @Autowired
    private UserRepo userRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer postCategoryId, Integer postUserId) {
        Post post = modelMapper.map(postDto, Post.class);
        post.setPostImageName("default.png");
        post.setPostAddedDate(new Date());
        User user = userRepo.findById(postUserId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User id", postUserId));
        
        Category category = categoryRepo.findById(postCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", postCategoryId));
                
        post.setUser(user);
        post.setCategory(category);
        Post createdPost = postRepo.save(post);
        return modelMapper.map(createdPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        postRepo.delete(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        post.setPostTitle(postDto.getPostTitle());
        post.setPostContent(postDto.getPostContent());
        post.setPostAddedDate(new Date());
        post.setPostImageName(postDto.getPostImageName());
        Post updatedPost = postRepo.save(post);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {

        // pagination code

        // int pageSize = 10;
        // int pageNumber = 0;
        Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

        Page<Post> pagePost = postRepo.findAll(p);
        List<Post> allPosts = pagePost.getContent();

        List<PostDto> postDtoList = allPosts.stream().map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;

        // getCode without pagination
        
        // List<Post> postList = postRepo.findAll();
        // List<PostDto> postDtoList = postList.stream().map(post -> modelMapper.map(post, PostDto.class))
        //         .collect(Collectors.toList());
        
        // return postDtoList;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPostsByCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        List<Post> postList = postRepo.findByCategory(category);
        List<PostDto> postDtoList = postList.stream().map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> getAllPostsByUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
        List<Post> postList = postRepo.findByUser(user);
        List<PostDto> postDtoList = postList.stream().map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> postList = postRepo.findByPostTitleContaining(keyword);
        List<PostDto> postDtoList = postList.stream().map((post) -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> searchPostByContent(String keyword) {
        List<Post> postList = postRepo.findByContent(keyword);
        List<PostDto> postDtoList = postList.stream().map((post) -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postDtoList;
    }
    
}
