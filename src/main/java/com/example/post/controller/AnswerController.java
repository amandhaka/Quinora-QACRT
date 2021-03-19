package com.example.post.controller;

import com.example.post.dto.AnswerRequestDTO;
import com.example.post.dto.AnswerRequestIdDTO;
import com.example.post.dto.AnswerResponseDTO;
import com.example.post.dto.SortAnswerPostDTO;
import com.example.post.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping(path ="{username}/{quid}")
    public String postAnswer (@PathVariable("quid") Long quid, @PathVariable("username") String username, @RequestBody AnswerRequestDTO request){
        return answerService.postAnswer(username, quid, request);

    }
    @GetMapping(path = "{username}/{qid}")
    public List<AnswerResponseDTO> showAnswers (@PathVariable("qid") Long qid, @PathVariable("username") String username){
        return answerService.showAnswers(username, qid) ;
    }

    @PutMapping(path = "{username}/{quid}")
    public String updateAnswer (@PathVariable("quid") Long quid, @PathVariable("username") String username, @RequestBody AnswerRequestIdDTO request){
        return answerService.updateAnswer(username, quid, request);
    }

    @PutMapping( path = "{username}/{quid}/delete")
    public String deleteAnswerFromUI(@PathVariable("quid") Long quid, @PathVariable("username") String username, @RequestBody AnswerRequestIdDTO request){
        return answerService.deleteAnswerFromUI(username, quid, request);
    }

    @PostMapping(path = "{username}/{qid}/sort")
    public List<AnswerResponseDTO> showAnswersAfterSort (@PathVariable("qid") Long qid, @PathVariable("username") String username, @RequestBody SortAnswerPostDTO request){
        return answerService.showAnswersAfterSort(username, qid, request) ;
    }

    @GetMapping(path = "/points")
    public Long findPoints(@RequestParam("username") String username) {
        return answerService.findPoints(username);
    }



}
