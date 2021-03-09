package com.example.post.dto;

import com.example.post.entity.Category;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuestionResponseDto {

    private Long questionId;
    private String questionTitle;
    private String questionText;
    private Date createdAt;
    private List<Category> categoryList;
    private String username;
}
