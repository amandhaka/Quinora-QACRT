package com.example.post.controller;

import com.example.post.dto.CategoryResponseDto;
import com.example.post.service.CategoryService;
import jdk.nashorn.internal.objects.annotations.ScriptClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
