package com.example.post.service;

import com.example.post.dto.CategoryRequestDto;
import com.example.post.dto.CategoryResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoryResponseDto> findAllCategory();

    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
}
