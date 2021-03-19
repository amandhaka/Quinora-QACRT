package com.example.post.service.impl;

import com.example.post.dto.*;
import com.example.post.entity.Answer;
import com.example.post.entity.Comment;
import com.example.post.repository.AnswerRepository;
import com.example.post.repository.NotificationRepository;
import com.example.post.repository.ReactionRepository;
import com.example.post.repository.commentRepository;
import com.example.post.service.AnswerService;
import com.example.post.service.ProducerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private ProducerService producerService;

    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    commentRepository commentRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    ReactionRepository reactionRepository;

    public String postAnswer (String username, Long quid, AnswerRequestDTO request){
        try {
            java.util.Date date = new java.util.Date();

            java.sql.Date timeStamp = new java.sql.Date(date.getTime());

            Answer answerToSave = new Answer();
            answerToSave.setAnswerText(request.getAnswerText());
            answerToSave.setUserName(username);
            answerToSave.setQuestionID(quid);
            answerToSave.setTimeStamp(timeStamp);
            answerToSave.setImgsrc(request.getImgsrc());
            answerToSave.setStatus(true);
            Answer savedAnswer = answerRepository.save(answerToSave);
            //producerService.sendMessageToSearchAfterAnswerUpdate(savedAnswer);
            return (answerToSave.getUserName() + "Posted an answer!");
        } catch ( NullPointerException ex ){
            return null;
        }

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
            answerResponseDTO.setLikes(reactionRepository.getLikesAndDislikes(answer.getId(), true));
            answerResponseDTO.setDislikes(reactionRepository.getLikesAndDislikes(answer.getId(), false));
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
            Answer savedAnswer=answerRepository.save(answer);
            producerService.sendMessageToSearchAfterAnswerUpdate(savedAnswer);
            return (username + "edited their answer");
        }
        return null;
    }


    public String deleteAnswerFromUI (String username, Long quid, AnswerRequestIdDTO request){

        try {
            Optional<Answer> answerFromDb = answerRepository.findById(request.getAnswerId());

            AnswerStatus answerStatus = new AnswerStatus();
            if (answerFromDb.isPresent()) {
                Answer answer = answerFromDb.get();
                answer.setStatus(false);
                answerRepository.save(answer);
                answerStatus.setStatus(false);
                answerStatus.setId(request.getAnswerId());
                answerStatus.setQuestionID(quid);
                producerService.updateAnswerSearch(answerStatus);
                return ("Answer has been deleted!");

            }
        } catch ( NullPointerException ex ){
            return "";
        }
        return null;
    }

    public List<AnswerResponseDTO> showAnswersAfterSort (String username, Long quid, SortAnswerPostDTO request){


        List<AnswerResponseDTO> toReturn = new ArrayList<>();
        List <Answer> listOfAnswersOnQuestion = answerRepository.answersOnQuestion(quid);
        listOfAnswersOnQuestion.forEach(answer -> {
            AnswerResponseDTO answerResponseDTO = new AnswerResponseDTO();
            BeanUtils.copyProperties(answer, answerResponseDTO);
            //answerResponseDTO.setImgsrc(multimediaClient.getPic());
            System.out.println(answerResponseDTO);
            List<Comment>comments = commentRepository.getCommentsByAnsId(answer.getId());
            System.out.println(comments);
            answerResponseDTO.setLikes(reactionRepository.getLikesAndDislikes(answer.getId(), true));
            answerResponseDTO.setDislikes(reactionRepository.getLikesAndDislikes(answer.getId(), false));
            answerResponseDTO.setCommentList(comments);
            toReturn.add(answerResponseDTO);

        });


        if (request.getParameter().equals("byDislikes")){

            Collections.sort(toReturn, Comparator.comparing(s -> s.getDislikes())) ;
            Collections.reverse(toReturn);

        }

        else if (request.getParameter().equals("byLikes")){

            //Collections.sort(toReturn, (AnswerResponseDTO a1, AnswerResponseDTO a2) -> a1.getDislikes().compareTo(a2.getDislikes()));
            Collections.sort(toReturn, Comparator.comparing(s -> s.getLikes()));
            Collections.reverse(toReturn);

        }

        else if (request.getParameter().equals("byNew")){

            Collections.reverse(toReturn);

        }

        return toReturn;

    }

    @Override
    public Long findPoints(String username) {
        Long numberOfLikes = reactionRepository.findNumberOfLikes(username);
        Long numberOfDislikes = reactionRepository.findNumberOfDislikes(username);
        Long numberOfAnswers = answerRepository.findAnswerCount(username);
        Long points = new Long(0);
        try {
            points = (numberOfLikes-numberOfDislikes)/numberOfAnswers;
        } catch(Exception ex ){
            points = new Long(0);
        }

        return points;
    }

}
