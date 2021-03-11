package com.example.post.dto;


import lombok.Data;

@Data
public class NotificationRequestDto {

    private String usernameAnswered;

    private Long questionId;

    private Long answerId;

    private boolean isRead;
}
