package com.example.post.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
public class Notification {

    @Id
    @GenericGenerator(name = "notification_id_seq", strategy = "increment")
    @GeneratedValue(generator = "notification_id_seq", strategy = GenerationType.AUTO)
    private Long notificationId;

    private String usernameAnswered;

    private Long questionId;

    private Long answerId;

    @Column(columnDefinition = "boolean default true")
    private boolean isRead;
}
