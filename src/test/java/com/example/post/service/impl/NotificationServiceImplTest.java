package com.example.post.service.impl;

import com.example.post.dto.NotificationRequestDto;
import com.example.post.entity.Notification;
import com.example.post.entity.Question;
import com.example.post.repository.NotificationRepository;
import com.example.post.repository.QuestionRepository;
import com.example.post.utility.NotificationUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import java.io.IOException;
import java.net.URL;
import java.util.*;
@RunWith(MockitoJUnitRunner.class)
class NotificationServiceImplTest {

    @InjectMocks
    private NotificationServiceImpl notificationService;
    @Mock
    private NotificationRepository notificationRepository;
    @Mock
    private QuestionRepository questionRepository;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void viewNotification() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> notificationObject = objectMapper.readValue(new URL("file:src/test/resources/notification.mock"), ArrayList.class);
        List<Notification> list = new ArrayList<>();
        for(Map<String, Object> obj : notificationObject) {
            Notification notification = new Notification();
            notification.setUsernameAnswered((String) obj.get("usernameAnswered"));
            notification.setQuestionId(Long.parseLong(String.valueOf(obj.get("questionId"))));
            notification.setNotificationId(Long.parseLong(String.valueOf(obj.get("notificationId"))));
            notification.setAnswerId(Long.parseLong(String.valueOf(obj.get("answerId"))));
            notification.setRead((Boolean) obj.get("read"));
            list.add(notification);
        }
        Question question = new Question();
        question.setQuestionId(1l);
        question.setCreatedAt(new Date());
        question.setStatus(true);
        question.setUsername("Aman");
        question.setQuestionTitle("uyfuyfu");
        question.setQuestionText("");
        question.setCategory("Movies");
        Mockito.when(notificationRepository.getNotificationByUsername("aman")).thenReturn(list);
        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        assertEquals(notificationService.viewNotification("aman").size(), 3);
        assertNull(notificationService.viewNotification("akshay"));
    }
    @Test
    public void sawNotification() {
        Notification notification1 = new Notification();
        notification1.setNotificationId(3L);
        notification1.setQuestionId(1L);
        notification1.setUsernameAnswered("preetham");
        notification1.setAnswerId(3L);
        notification1.setRead(true);
        Notification notification2 = new Notification();
        notification2.setNotificationId(4L);
        notification2.setQuestionId(1L);
        notification2.setUsernameAnswered("shikar");
        notification2.setAnswerId(3L);
        notification2.setRead(true);
        Mockito.when(notificationRepository.findById(3L)).thenReturn(Optional.of(notification1));
        Mockito.when(notificationRepository.findById(4L)).thenReturn(Optional.of(notification2));
        Mockito.when(notificationRepository.save(notification1)).thenReturn(notification1);
        Mockito.when(notificationRepository.save(notification2)).thenReturn(notification2);
        notificationService.sawNotification(3L);
        notificationService.sawNotification(4L);
    }
    @Test
    public void addNotification() {
        NotificationUtility notificationUtility = new NotificationUtility();
        Notification notification = notificationUtility.getNotification();
        NotificationRequestDto notificationRequestDto = new NotificationRequestDto();
        notificationRequestDto.setAnswerId(1L);
        notificationRequestDto.setUsernameAnswered("Swastik");
        notificationRequestDto.setQuestionId(3L);
        notificationRequestDto.setRead(true);
        Mockito.when(notificationRepository.save(notification)).thenReturn(notification);
        notificationService.addNotification(notificationRequestDto);
    }
}