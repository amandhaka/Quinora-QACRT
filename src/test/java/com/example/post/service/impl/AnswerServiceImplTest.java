package com.example.post.service.impl;

import com.example.post.dto.AnswerRequestDTO;
import com.example.post.dto.AnswerRequestIdDTO;
import com.example.post.dto.AnswerStatus;
import com.example.post.entity.Answer;
import com.example.post.repository.AnswerRepository;
import com.example.post.repository.QuestionRepository;
import com.example.post.repository.commentRepository;
import com.example.post.service.ProducerService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
@RunWith(MockitoJUnitRunner.class)
public class AnswerServiceImplTest {
    @InjectMocks
    private AnswerServiceImpl answerService;
    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private ProducerService producerService;
    @BeforeEach
    public void init()
    {
        MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    public void destroy()
    {
    }
    @Test
    public void postAnswer() throws Exception
    {
        String name="Ashish";
        java.util.Date date = new java.util.Date();
        java.sql.Date timeStamp = new java.sql.Date(date.getTime());
        String username="chaitanya";
        Long quid=1L;
        AnswerRequestDTO answerRequestDTO=new AnswerRequestDTO();
        answerRequestDTO.setAnswerText("hello Chaitanya");
        answerRequestDTO.setImgsrc("pokemon.jpeg");
        Answer answer=new Answer();
        answer.setStatus(true);
        answer.setImgsrc("pokemon.jpeg");
        answer.setAnswerText("hello Chaitanya");
        answer.setTimeStamp(timeStamp);
        answer.setId(1L);
        answer.setQuestionID(quid);
        answer.setUserName(name);
        Mockito.when(answerRepository.save(any(Answer.class))).thenReturn(answer);
        //producerService.sendMessageToSearchAfterAnswerUpdate(answer);
        assertEquals(answerService.postAnswer(name,quid,answerRequestDTO),answer.getUserName()+"Posted an answer!");
//        Mockito.when(answerService.postAnswer(name,quid,answerRequestDTO)).thenThrow(NullPointerException.class);
//        assertEquals(answerService.postAnswer(name,quid,answerRequestDTO),null);null
    }
    @Test
    public void updateAnswer() throws Exception
    {
        java.util.Date date = new java.util.Date();
        java.sql.Date timeStamp = new java.sql.Date(date.getTime());
        String username = "updaterUsername";
        Long quid = 1L;
        AnswerRequestIdDTO answerRequestIdDTO = new AnswerRequestIdDTO();
        answerRequestIdDTO.setAnswerId(1L);
        answerRequestIdDTO.setAnswerText("Updated answer text");
        answerRequestIdDTO.setImgsrc("Jesse.jpeg");
        Answer answer = new Answer();
        answer.setStatus(true);
        answer.setImgsrc("pokemon.jpeg");
        answer.setAnswerText("hello Chaitanya");
        answer.setTimeStamp(timeStamp);
        answer.setId(1L);
        answer.setQuestionID(quid);
        answer.setUserName(username);
        Optional<Answer> answerOptional = Optional.of(answer);
        Mockito.when(answerRepository.findById(any(Long.class))).thenReturn(answerOptional);
        Mockito.when(answerRepository.findById(null)).thenReturn(null);
        assertEquals(answerService.updateAnswer(username,quid,answerRequestIdDTO),username + "edited their answer");
    }
    @Test
    public void deleteAnswerFromUI() throws Exception{
        java.util.Date date = new java.util.Date();
        java.sql.Date timeStamp = new java.sql.Date(date.getTime());
        String username = "updaterUsername";
        Long quid = 1L;
        AnswerRequestIdDTO answerRequestIdDTO = new AnswerRequestIdDTO();
        answerRequestIdDTO.setAnswerId(1L);
        answerRequestIdDTO.setAnswerText("Updated answer text");
        answerRequestIdDTO.setImgsrc("Jesse.jpeg");
        Answer answer = new Answer();
        answer.setStatus(true);
        answer.setImgsrc("pokemon.jpeg");
        answer.setAnswerText("hello Chaitanya");
        answer.setTimeStamp(timeStamp);
        answer.setId(1L);
        answer.setQuestionID(quid);
        answer.setUserName(username);
        AnswerStatus answerStatus = new AnswerStatus();
        producerService.updateAnswerSearch(answerStatus);
        Optional<Answer> answerOptional = Optional.of(answer);
        Mockito.when(answerRepository.findById(any(Long.class))).thenReturn(answerOptional);
        Mockito.when(answerRepository.findById(null)).thenReturn(null);
       // Mockito.when(answerService.deleteAnswerFromUI(username, quid, answerRequestIdDTO)).thenReturn("deleted");
        assertEquals(answerService.deleteAnswerFromUI(username, quid, answerRequestIdDTO), "Answer has been deleted!");
        Mockito.when(answerService.deleteAnswerFromUI("", null, null)).thenThrow(NullPointerException.class);
    }
}
