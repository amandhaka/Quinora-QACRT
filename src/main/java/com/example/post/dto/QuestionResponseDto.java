package com.example.post.dto;

import com.example.post.entity.Category;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Data
public class QuestionResponseDto {
    private Long questionId;
    private String questionTitle;
    private String questionText;
    private Date createdAt;
    private String username;
    private boolean status;
    private List<Category> categoryList;
}
