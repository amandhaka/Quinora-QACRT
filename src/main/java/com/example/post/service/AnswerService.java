package com.example.post.service;


import com.example.post.dto.AnswerRequestDTO;
import com.example.post.dto.AnswerRequestIdDTO;
import com.example.post.dto.AnswerResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AnswerService {
    String postAnswer (String username, Long quid, AnswerRequestDTO request);

    List<AnswerResponseDTO> showAnswers (String username, Long quid);

    String updateAnswer (String username, Long quid, AnswerRequestIdDTO request);

    String deleteAnswerFromUI (String username, Long quid, AnswerRequestIdDTO request);
}
