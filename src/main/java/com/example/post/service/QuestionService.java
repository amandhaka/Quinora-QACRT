package com.example.post.service;

import com.example.post.dto.QuestionRequestDto;
import com.example.post.dto.QuestionResponseDto;

import java.util.List;

public interface QuestionService {


    List<QuestionResponseDto> getAllQuestions();

    QuestionResponseDto createQuestion(String username, QuestionRequestDto questionRequestDto);

    QuestionResponseDto editQuestion(String username, Long questionId, QuestionRequestDto questionRequestDto);

    List<QuestionResponseDto> questionListByCategory(Long categoryId);

    List<QuestionResponseDto> questionListByUsername(String username);

    QuestionResponseDto deleteQuestionById(String username, Long questionId);

    QuestionResponseDto disableQuestionById(String username, Long questionId);
}
