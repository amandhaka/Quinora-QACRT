package com.example.post.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Comment {
    @Id
    private Long id;
    private Long answerId;
    private String commentText;
    private Boolean status;
}
