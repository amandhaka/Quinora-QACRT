package com.example.post.dto;

import com.example.post.entity.Category;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;

@Data
public class QuestionRequestDto {
    private String questionTitle;
    private String questionText;
    private Date createdAt;
    private String category;
    private boolean status;
    private String questionImage;
}
