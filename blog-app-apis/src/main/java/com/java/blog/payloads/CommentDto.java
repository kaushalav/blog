package com.java.blog.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private int commentId;
    private String commentContent;
}
