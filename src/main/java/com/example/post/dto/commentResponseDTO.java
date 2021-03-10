package com.example.post.dto;

import lombok.Data;

import java.util.Date;

@Data
public class commentResponseDTO {
    private Long commentId;
    private String userName;
    private Date timeStamp;
    private String commentText;
}
