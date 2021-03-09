package com.example.post.service;

import com.example.post.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> findAllCategory();
}
