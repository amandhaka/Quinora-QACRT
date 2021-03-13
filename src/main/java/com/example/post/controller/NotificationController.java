package com.example.post.controller;

import com.example.post.dto.NotificationRequestDto;
import com.example.post.dto.NotificationResponseDto;
import com.example.post.entity.Notification;
import com.example.post.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @CrossOrigin
    @PostMapping("/add")
    public String addNotification(@RequestBody NotificationRequestDto notificationRequestDto) {
        return notificationService.addNotification(notificationRequestDto);
    }

    @CrossOrigin
    @GetMapping("/{username}/viewNotification")
    public List<NotificationResponseDto> viewNotification(@PathVariable("username") String username){
        return notificationService.viewNotification(username);
    }
    @CrossOrigin
    @PutMapping("/updateNotification/{notificationId}")
    public String sawNotification(@PathVariable("notificationId") Long notificationId) {
        return notificationService.sawNotification(notificationId);
    }
    @CrossOrigin
    @GetMapping("/{username}/count")
    public Long newNotificationCount(@PathVariable("username") String username) {
        return notificationService.newNotificationCount(username);
    }
}
