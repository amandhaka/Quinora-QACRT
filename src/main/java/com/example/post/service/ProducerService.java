package com.example.post.service;

import com.example.post.dto.AnswerStatus;
import com.example.post.dto.QuestionStatus;
import com.example.post.entity.Answer;
import com.example.post.entity.Question;

public interface ProducerService {

    void sendMessageToSearchAfterUpdate(Question question);
    void sendMessageToSearchAfterAnswerUpdate(Answer answer);
    void updateQuestion(QuestionStatus questionStatus);
    void updateAnswerSearch(AnswerStatus answerStatus);
}
