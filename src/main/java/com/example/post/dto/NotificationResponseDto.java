package com.example.post.dto;

import lombok.Data;

@Data
public class NotificationResponseDto {

    private Long notificationId;

    private String usernameAnswered;

    private Long questionId;

    private String questionTitle;

    private Long answerId;

    private boolean isRead;

}
