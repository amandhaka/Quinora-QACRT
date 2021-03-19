package com.example.post.controller;

import com.example.post.dto.ReactionRequestDto;
import com.example.post.dto.ReactionResponseDTO;
import com.example.post.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reaction")

public class ReactionController {

    @Autowired
    ReactionService reactionService;

    @PostMapping("/{username}")
    public ReactionResponseDTO createReaction(@PathVariable("username") String username, @RequestBody ReactionRequestDto reactionRequestDto) {
        return reactionService.createReaction(username, reactionRequestDto);
    }

//    @GetMapping("/{username}/{answer_id}")
//    public Long[] getLikesAndDislikes(@PathVariable("username") String username, @PathVariable("answer_id") Long answer_id) {
//        return reactionService.getLikesAndDislikes(username, answer_id);
//    }
}