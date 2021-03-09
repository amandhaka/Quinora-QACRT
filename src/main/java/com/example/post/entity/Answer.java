package com.example.post.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "answer")
public class Answer {
    @Id
    private Long answerId;
}
