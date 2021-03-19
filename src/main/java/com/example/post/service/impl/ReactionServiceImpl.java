package com.example.post.service.impl;

import com.example.post.dto.ReactionRequestDto;
import com.example.post.dto.ReactionResponseDTO;
import com.example.post.entity.AnswerReaction;
import com.example.post.repository.ReactionRepository;
import com.example.post.service.ReactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactionServiceImpl implements ReactionService {

    @Autowired
    ReactionRepository reactionRepository;

    @Override
    public ReactionResponseDTO createReaction(String username, ReactionRequestDto reactionRequestDto) {
        if(reactionRepository.checkIfPresent(reactionRequestDto.getAnswerId(), username)) {
            AnswerReaction ruser = reactionRepository.getUser(reactionRequestDto.getAnswerId(), username);
//            ruser.setLiked(reactionRequestDto.isLiked());
//            System.out.println(ruser.isLiked());
//            reactionRepository.save(ruser);
            if(ruser.isLiked() && reactionRequestDto.isLiked())
                reactionRepository.delete(ruser);
            else if(ruser.isLiked()==false && reactionRequestDto.isLiked()) {
                ruser.setLiked(true);
                reactionRepository.save(ruser);
            }
            else if(ruser.isLiked()==false && reactionRequestDto.isLiked()==false)
                reactionRepository.delete(ruser);
            else if(ruser.isLiked() && reactionRequestDto.isLiked()==false) {
                ruser.setLiked(false);
                reactionRepository.save(ruser);
            }
        }
        else {
            AnswerReaction answerReaction = new AnswerReaction();
            BeanUtils.copyProperties(reactionRequestDto, answerReaction);
            answerReaction.setUsername(username);
            reactionRepository.save(answerReaction);
        }
        ReactionResponseDTO reactionResponseDTO = new ReactionResponseDTO();
        reactionResponseDTO.setAnswerId(reactionRequestDto.getAnswerId());
        reactionResponseDTO.setLikesCount(reactionRepository.getLikesAndDislikes(reactionRequestDto.getAnswerId(),true));
        reactionResponseDTO.setDislikesCount(reactionRepository.getLikesAndDislikes(reactionRequestDto.getAnswerId(),false));
        return reactionResponseDTO;
    }

//    @Override
//    public Long[] getLikesAndDislikes(String username, Long answer_id) {
//        Long[] countOfLikesAndDislikes = new Long[2];
//        countOfLikesAndDislikes[0] = reactionRepository.getLikesAndDislikes(answer_id,true);
//        countOfLikesAndDislikes[1] = reactionRepository.getLikesAndDislikes(answer_id,false);
//
//        return countOfLikesAndDislikes;
//    }

}
