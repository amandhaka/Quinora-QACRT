package com.example.post.utility;

import com.example.post.entity.AnswerReaction;
import com.example.post.entity.Notification;
import lombok.Data;
@Data
public class NotificationUtility {
    public Notification notification = null;
    public Notification getNotification() {
        notification = new Notification();
        notification.setRead(true);
        notification.setAnswerId(1L);
        notification.setUsernameAnswered("Swastik");
        notification.setQuestionId(3L);
        notification.setNotificationId(5L);
        return notification;
    }
}
