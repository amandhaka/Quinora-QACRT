package com.example.post.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Notification {

    @Id
    @GenericGenerator(name = "notification_id_seq", strategy = "increment")
    @GeneratedValue(generator = "notification_id_seq", strategy = GenerationType.AUTO)
    private Long notificationId;
    @Column(nullable = false)
    private String usernameAnswered;

    @Column(nullable = false)
    private Long questionId;

    private Long answerId;

    @Column(nullable = false)
    private boolean isRead = true;
}
