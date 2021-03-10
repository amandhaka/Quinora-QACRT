package com.example.post.dto;

import lombok.Data;

import java.util.Date;

@Data
public class updateCommentRequestDTO {
    private Long commentId;
    private String newCommentText;
    private Date date;
}
