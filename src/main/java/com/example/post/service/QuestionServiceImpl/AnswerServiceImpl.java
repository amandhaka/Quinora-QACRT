package com.example.post.service.impl;

import com.example.post.dto.AnswerRequestDTO;
import com.example.post.dto.AnswerRequestIdDTO;
import com.example.post.dto.AnswerResponseDTO;
import com.example.post.entity.Answer;
import com.example.post.entity.Comment;
import com.example.post.repository.AnswerRepository;
import com.example.post.repository.commentRepository;
import com.example.post.service.AnswerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    commentRepository commentRepository;

//    @Autowired
//    private MultimediaClient multimediaClient;

    public String postAnswer (String username, Long quid, AnswerRequestDTO request){


        java.util.Date date = new java.util.Date();
        java.sql.Date timeStamp = new java.sql.Date(date.getTime());

        Answer answerToSave = new Answer();
        answerToSave.setAnswerText(request.getAnswerText());
        answerToSave.setUserName(username);
        answerToSave.setQuestionID(quid);
        answerToSave.setTimeStamp(timeStamp);
        answerToSave.setImgsrc(request.getImgsrc());
        answerToSave.setStatus(true);

//        Map<String, Long> multimediaClientIdsMap = multimediaClient.getIds(request);
//        answerToSave.setImageID(multimediaClientIdsMap.get(imageId));
        answerRepository.save(answerToSave);


        return (answerToSave.getUserName()+"Posted an answer!");

    }

    public List<AnswerResponseDTO> showAnswers (String username, Long quid){
        List<AnswerResponseDTO> toReturn = new ArrayList<>();
        List <Answer> listOfAnswersOnQuestion = answerRepository.answersOnQuestion(quid);
        listOfAnswersOnQuestion.forEach(answer -> {
            AnswerResponseDTO answerResponseDTO = new AnswerResponseDTO();
            BeanUtils.copyProperties(answer, answerResponseDTO);
            System.out.println(answerResponseDTO);
            List<Comment>comments = commentRepository.getCommentsByAnsId(answer.getId());
            System.out.println(comments);
            answerResponseDTO.setCommentList(comments);
            System.out.println(answerResponseDTO);
            toReturn.add(answerResponseDTO);

        });

            return toReturn;


    }

    public String updateAnswer (String username, Long quid, AnswerRequestIdDTO request){
        Optional<Answer> answerFromDb = answerRepository.findById(request.getAnswerId());
        if (answerFromDb.isPresent()){
            Answer answer = answerFromDb.get();
            answer.setAnswerText(request.getAnswerText());
            if (request.getImgsrc() != "") {
                answer.setImgsrc(request.getImgsrc());
            }
            answerRepository.save(answer);
            return (username + "edited their answer");
        }

        return null;


    }


    public String deleteAnswerFromUI (String username, Long quid, AnswerRequestIdDTO request){
        Optional<Answer> answerFromDb = answerRepository.findById(request.getAnswerId());
        if (answerFromDb.isPresent()){
            Answer answer = answerFromDb.get();
            answer.setStatus(false);
            return ("Answer has been deleted!");

        }

        return null;

    }

}