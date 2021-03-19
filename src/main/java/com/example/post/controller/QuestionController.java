package com.example.post.controller;

import com.example.post.dto.*;
import com.example.post.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all")
    List<QuestionResponseDto> getAllQuestions() {
        return questionService.getAllQuestions();
    }


    @PostMapping("/{username}/add")
    QuestionResponseDto createQuestion(@PathVariable("username") String username, @RequestBody QuestionRequestDto questionRequestDto) throws Exception {
        return questionService.createQuestion(username, questionRequestDto);
    }


    @PutMapping("/{username}/edit/{questionId}")
    QuestionResponseDto editQuestion(@PathVariable("username") String username, @PathVariable("questionId") Long questionId, @RequestBody QuestionRequestDto questionRequestDto) {
        return questionService.editQuestion(username, questionId, questionRequestDto);
    }


    @GetMapping("/")
    List<QuestionResponseDto> questionListByCategory(@RequestParam("category") String category) {
        return questionService.questionListByCategory(category);
    }


    @GetMapping("/all/{username}")
    List<QuestionResponseDto> questionListByUsername(@PathVariable("username") String username) {
        return questionService.questionListByUsername(username);
    }


    @DeleteMapping("/{username}/delete/{questionId}")
    QuestionResponseDto deleteQuestionById(@PathVariable("username") String username, @PathVariable("questionId") Long questionId) {
        return questionService.deleteQuestionById(username, questionId);
    }


    @PutMapping("/{username}/disable/{questionId}")
    QuestionResponseDto disableQuestionById(@PathVariable("username") String username, @PathVariable("questionId") Long questionId) {
        return questionService.disableQuestionById(username, questionId);
    }

    @GetMapping("/question-id/{questionId}")
    QuestionResponseDto questionByQuestionId(@PathVariable("questionId") Long questionId) {
        return questionService.questionByQuestionId(questionId);
    }
}
