package com.example.post.service;

import com.example.post.dto.ReactionRequestDto;
import com.example.post.dto.ReactionResponseDTO;

public interface ReactionService {
    ReactionResponseDTO createReaction(String username, ReactionRequestDto reactionRequestDto);

    //Long[] getLikesAndDislikes(String username, Long answer_id);
}
