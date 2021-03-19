package com.example.post.service.impl;

import com.example.post.dto.NotificationRequestDto;
import com.example.post.dto.NotificationResponseDto;
import com.example.post.entity.Notification;
import com.example.post.repository.NotificationRepository;
import com.example.post.repository.QuestionRepository;
import com.example.post.service.NotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public String addNotification(NotificationRequestDto notificationRequestDto) {
        Notification notification = new Notification();
        BeanUtils.copyProperties(notificationRequestDto, notification);
        notificationRepository.save(notification);
        return "success";
    }

    @Override
    public List<NotificationResponseDto> viewNotification(String username) {
        List<Notification> notificationList = notificationRepository.getNotificationByUsername(username);
        List<NotificationResponseDto> notificationResponseDtoList = new ArrayList<>();
        if(notificationList.size()!=0) {
            for(Notification notification: notificationList) {
                NotificationResponseDto responseDto = new NotificationResponseDto();
                BeanUtils.copyProperties(notification, responseDto);
                responseDto.setQuestionTitle(questionRepository.findById(notification.getQuestionId()).get().getQuestionTitle());
                //responseDto.setQuestionTitle(questionRepository.findById(1l).get().getQuestionTitle());
                notificationResponseDtoList.add(responseDto);
            }
            return notificationResponseDtoList;
        }
        return null;
    }
    @Override
    public String sawNotification(Long notificationId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        notification.get().setRead(false);
        notificationRepository.save(notification.get());
        return "success";
    }

    @Override
    public Long newNotificationCount(String username) {
        Long notificationCount = notificationRepository.getNoticiationCount(username);
        return notificationCount;
    }
}
