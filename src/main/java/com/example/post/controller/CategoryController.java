package com.example.post.controller;

import com.example.post.dto.CategoryRequestDto;
import com.example.post.dto.CategoryResponseDto;
import com.example.post.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/all")
    List<CategoryResponseDto> findAllCategory() {
       return categoryService.findAllCategory();
    }
//
    @PostMapping("/add/category")
    CategoryResponseDto createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryService.createCategory(categoryRequestDto);
    }
}
