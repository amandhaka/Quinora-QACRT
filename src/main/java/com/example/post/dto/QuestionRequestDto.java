package com.example.post.dto;

import com.example.post.entity.Category;
import lombok.Data;

import java.util.List;

@Data
public class QuestionRequestDto {
    private String questionTitle;
    private String questionText;
    private List<Category> categoryList;
    private String username;
}
