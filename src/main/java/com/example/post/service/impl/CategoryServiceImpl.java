package com.example.post.service.impl;

import com.example.post.dto.CategoryResponseDto;
import com.example.post.entity.Category;
import com.example.post.repository.CategoryRepository;
import com.example.post.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryResponseDto> findAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();
        for(Category category: categoryList) {
            CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
            BeanUtils.copyProperties(category, categoryResponseDto);
            categoryResponseDtoList.add(categoryResponseDto);
        }
        return categoryResponseDtoList;
    }
}
