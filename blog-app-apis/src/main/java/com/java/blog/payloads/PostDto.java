package com.java.blog.payloads;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
public class PostDto {
    private int postId;
    private String postTitle;
    private String postContent;
    private Date postAddedDate;
    private String postImageName;
    private CategoryDto category;
    private UserDto user;
}
