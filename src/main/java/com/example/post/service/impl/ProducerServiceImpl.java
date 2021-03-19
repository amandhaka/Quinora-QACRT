package com.example.post.service.impl;

import com.example.post.dto.AnswerStatus;
import com.example.post.dto.QuestionStatus;
import com.example.post.entity.Answer;
import com.example.post.entity.Question;
import com.example.post.service.ProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ProducerServiceImpl.class);
    protected static final String UPDATE_BY_QUESTION_TO_SEARCH_AFTER_UPDATE = "updateSearchQA";
    protected static final String UPDATE_BY_ANSWER_TO_SEARCH_AFTER_UPDATE = "updateSearchAnswer";
    protected static final String UPDATE_BY_QUESTION_STATUS = "disableQuestion";
    protected static final String UPDATE_BY_ANSWER_STATUS = "disableAnswer";
    @Override
    public void sendMessageToSearchAfterUpdate(Question question)
    {
        logger.info(String.format("#### -> Producing Message -> %s",question.toString()));
        ObjectMapper objectMapper = new ObjectMapper();
        String string = "";
        try{
            string = objectMapper.writeValueAsString(question);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        kafkaTemplate.send(UPDATE_BY_QUESTION_TO_SEARCH_AFTER_UPDATE, string);
        logger.info(String.format("Sent to search microservice"));
    }

    @Override
    public void sendMessageToSearchAfterAnswerUpdate(Answer answer)
    {
        logger.info(String.format("#### -> Producing Message -> %s",answer.toString()));
        ObjectMapper objectMapper = new ObjectMapper();
        String string = "";
        try{
            string = objectMapper.writeValueAsString(answer);
        }
        //catch exception
        catch (Exception e)
        {
            e.printStackTrace();
        }
        kafkaTemplate.send(UPDATE_BY_ANSWER_TO_SEARCH_AFTER_UPDATE, string);
        logger.info(String.format("Sent to search microservice"));
    }

    @Override
    public void updateQuestion(QuestionStatus questionStatus)
    {
        logger.info(String.format("#### -> Producing Message -> %s",questionStatus.toString()));
        ObjectMapper objectMapper = new ObjectMapper();
        String string = "";
        try{
            string = objectMapper.writeValueAsString(questionStatus);
        }
        //catch exception
        catch (Exception e)
        {
            e.printStackTrace();
        }
        kafkaTemplate.send(UPDATE_BY_QUESTION_STATUS, string);
        logger.info(String.format("Sent to search microservice"));
    }

    @Override
    public void updateAnswerSearch(AnswerStatus answerStatus)
    {
        logger.info(String.format("#### -> Producing Message -> %s",answerStatus.toString()));
        ObjectMapper objectMapper = new ObjectMapper();
        String string = "";
        try{
            string = objectMapper.writeValueAsString(answerStatus);
        }
        //catch exception
        catch (Exception e)
        {
            e.printStackTrace();
        }
        kafkaTemplate.send(UPDATE_BY_ANSWER_STATUS, string);
        logger.info(String.format("Sent to search microservice"));
    }
}
