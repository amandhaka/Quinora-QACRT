package com.example.post.service;


import com.example.post.dto.AnswerRequestDTO;
import com.example.post.dto.AnswerRequestIdDTO;
import com.example.post.dto.AnswerResponseDTO;
import com.example.post.dto.SortAnswerPostDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AnswerService {
    String postAnswer (String username, Long quid, AnswerRequestDTO request);

    List<AnswerResponseDTO> showAnswers (String username, Long quid);

    String updateAnswer (String username, Long quid, AnswerRequestIdDTO request);

    String deleteAnswerFromUI (String username, Long quid, AnswerRequestIdDTO request);

    List<AnswerResponseDTO> showAnswersAfterSort (String username, Long quid, SortAnswerPostDTO request);

    Long findPoints(String username);
}
