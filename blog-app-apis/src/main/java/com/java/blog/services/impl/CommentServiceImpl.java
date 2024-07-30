package com.java.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.blog.entities.Comment;
import com.java.blog.entities.Post;
import com.java.blog.exception.ResourceNotFoundException;
import com.java.blog.payloads.CommentDto;
import com.java.blog.repositories.CommentRepo;
import com.java.blog.repositories.PostRepo;
import com.java.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
    
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));    
        Comment comment = modelMapper.map(commentDto, Comment.class);
        
        comment.setPost(post);
        Comment savedComment = commentRepo.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        commentRepo.delete(comment);
    }
    
}
