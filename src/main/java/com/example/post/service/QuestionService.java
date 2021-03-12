package com.example.post.service;

import com.example.post.dto.CategoryRequestDto;
import com.example.post.dto.CategoryResponseDto;
import com.example.post.dto.QuestionRequestDto;
import com.example.post.dto.QuestionResponseDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {

    List<QuestionResponseDto> getAllQuestions();

    QuestionResponseDto createQuestion(String username, QuestionRequestDto questionRequestDto) throws Exception;

    QuestionResponseDto editQuestion(String username, Long questionId, QuestionRequestDto questionRequestDto);

    List<QuestionResponseDto> questionListByUsername(String username);

    QuestionResponseDto deleteQuestionById(String username, Long questionId);

    List<QuestionResponseDto> questionListByCategory(String category);

    QuestionResponseDto disableQuestionById(String username, Long questionId);

    QuestionResponseDto questionByQuestionId(Long questionId);

}

