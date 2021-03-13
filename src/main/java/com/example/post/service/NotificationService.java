package com.example.post.service;

import com.example.post.dto.NotificationRequestDto;
import com.example.post.dto.NotificationResponseDto;

import java.util.List;

public interface NotificationService {
    public String addNotification(NotificationRequestDto notificationRequestDto);

    List<NotificationResponseDto> viewNotification(String username);

    String sawNotification(Long notificationId);

    Long newNotificationCount(String username);
}
