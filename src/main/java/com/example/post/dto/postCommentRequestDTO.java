package com.example.post.dto;

import lombok.Data;

import java.util.Date;

@Data
public class postCommentRequestDTO {
    private Long answerId;
    private String text;
    private Date date;
}
