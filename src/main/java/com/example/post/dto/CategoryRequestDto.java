package com.example.post.dto;

import com.example.post.entity.Question;
import lombok.Data;

import java.util.Set;

@Data
public class CategoryRequestDto {
    private Long categoryId;
    private String categoryName;
}
