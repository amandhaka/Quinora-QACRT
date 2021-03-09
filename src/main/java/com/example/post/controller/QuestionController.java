package com.example.post.controller;

import com.example.post.dto.CategoryRequestDto;
import com.example.post.dto.CategoryResponseDto;
import com.example.post.dto.QuestionRequestDto;
import com.example.post.dto.QuestionResponseDto;
import com.example.post.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @CrossOrigin
    @GetMapping("/all")
    List<QuestionResponseDto> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @CrossOrigin
    @PostMapping("/{username}/add")
    QuestionResponseDto createQuestion(@PathVariable("username") String username, @RequestBody QuestionRequestDto questionRequestDto) throws Exception {
        return questionService.createQuestion(username, questionRequestDto);
    }

    @CrossOrigin
    @PutMapping("/{username}/edit/{questionId}")
    QuestionResponseDto editQuestion(@PathVariable("username") String username, @PathVariable("questionId") Long questionId, @RequestBody QuestionRequestDto questionRequestDto) {
        return questionService.editQuestion(username, questionId, questionRequestDto);
    }

    @CrossOrigin
    @GetMapping("/{category}")
    List<QuestionResponseDto> questionListByCategory(@PathVariable("category") String category) {
        return questionService.questionListByCategory(category);
    }

    @CrossOrigin
    @GetMapping("/all/{username}")
    List<QuestionResponseDto> questionListByUsername(@PathVariable("username") String username) {
        return questionService.questionListByUsername(username);
    }

    @CrossOrigin
    @DeleteMapping("/{username}/delete/{questionId}")
    QuestionResponseDto deleteQuestionById(@PathVariable("username") String username, @PathVariable("questionId") Long questionId) {
        return questionService.deleteQuestionById(username, questionId);
    }

    @CrossOrigin
    @PutMapping("/{username}/disable/{questionId}")
    QuestionResponseDto disableQuestionById(@PathVariable("username") String username, @PathVariable("questionId") Long questionId) {
        return questionService.disableQuestionById(username, questionId);
    }

}
