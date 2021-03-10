package com.example.post.dto;

import lombok.Data;

import java.util.Date;

@Data
public class deleteCommentRequestDTO {
    private Long commentId;
    private Date date;
}
