package com.example.post.service.impl;
import com.example.post.dto.ReactionRequestDto;
import com.example.post.entity.AnswerReaction;
import com.example.post.repository.ReactionRepository;
import com.example.post.utility.ReactionUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class ReactionServiceImplTest {
    @InjectMocks
    private ReactionServiceImpl reactionService;
    @Mock
    private ReactionRepository reactionRepository;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void createReaction() {
        ReactionUtility reactionUtility = new ReactionUtility();
        AnswerReaction reaction = reactionUtility.getAnswerReaction();
        ReactionRequestDto reactionRequestDto = new ReactionRequestDto();
        reactionRequestDto.setAnswerId(1L);
        reactionRequestDto.setLiked(true);
        Mockito.when(reactionRepository.checkIfPresent(1L, "Akshay")).thenReturn(true);
        Mockito.when(reactionRepository.getUser(1L, "Akshay")).thenReturn(reaction);
        Mockito.when(reactionRepository.save(reaction)).thenReturn(reaction);
        Mockito.when(reactionRepository.getLikesAndDislikes(reactionRequestDto.getAnswerId(), true)).thenReturn(1L);
        Mockito.when(reactionRepository.getLikesAndDislikes(reactionRequestDto.getAnswerId(), false)).thenReturn(0L);
        reactionService.createReaction(reaction.getUsername(), reactionRequestDto);
    }
    @Test
    public void createReaction2() {
        ReactionUtility reactionUtility = new ReactionUtility();
        AnswerReaction reaction = reactionUtility.getAnswerReaction();
        ReactionRequestDto reactionRequestDto2 = new ReactionRequestDto();
        reactionRequestDto2.setAnswerId(1L);
        reactionRequestDto2.setLiked(false);
        Mockito.when(reactionRepository.checkIfPresent(1L, "Akshay")).thenReturn(true);
        Mockito.when(reactionRepository.getUser(1L, "Akshay")).thenReturn(reaction);
        Mockito.when(reactionRepository.save(reaction)).thenReturn(reaction);
        Mockito.when(reactionRepository.getLikesAndDislikes(reactionRequestDto2.getAnswerId(), true)).thenReturn(1L);
        Mockito.when(reactionRepository.getLikesAndDislikes(reactionRequestDto2.getAnswerId(), false)).thenReturn(0L);
        reactionService.createReaction(reaction.getUsername(), reactionRequestDto2);
    }
    @Test
    public void createReaction3() {
        ReactionUtility reactionUtility = new ReactionUtility();
        AnswerReaction reaction = reactionUtility.getAnswerReaction2();
        ReactionRequestDto reactionRequestDto = new ReactionRequestDto();
        reactionRequestDto.setAnswerId(1L);
        reactionRequestDto.setLiked(true);
        Mockito.when(reactionRepository.checkIfPresent(1L, "Akshay")).thenReturn(true);
        Mockito.when(reactionRepository.getUser(1L, "Akshay")).thenReturn(reaction);
        Mockito.when(reactionRepository.save(reaction)).thenReturn(reaction);
        Mockito.when(reactionRepository.getLikesAndDislikes(reactionRequestDto.getAnswerId(), true)).thenReturn(1L);
        Mockito.when(reactionRepository.getLikesAndDislikes(reactionRequestDto.getAnswerId(), false)).thenReturn(0L);
        reactionService.createReaction(reaction.getUsername(), reactionRequestDto);
    }
    @Test
    public void createReaction4() {
        ReactionUtility reactionUtility = new ReactionUtility();
        AnswerReaction reaction = reactionUtility.getAnswerReaction2();
        ReactionRequestDto reactionRequestDto2 = new ReactionRequestDto();
        reactionRequestDto2.setAnswerId(1L);
        reactionRequestDto2.setLiked(false);
        Mockito.when(reactionRepository.checkIfPresent(1L, "Akshay")).thenReturn(true);
        Mockito.when(reactionRepository.getUser(1L, "Akshay")).thenReturn(reaction);
        Mockito.when(reactionRepository.save(reaction)).thenReturn(reaction);
        Mockito.when(reactionRepository.getLikesAndDislikes(reactionRequestDto2.getAnswerId(), true)).thenReturn(1L);
        Mockito.when(reactionRepository.getLikesAndDislikes(reactionRequestDto2.getAnswerId(), false)).thenReturn(0L);
        reactionService.createReaction(reaction.getUsername(), reactionRequestDto2);
    }
    @Test
    public void createReaction5() {
        ReactionUtility reactionUtility = new ReactionUtility();
        AnswerReaction reaction = reactionUtility.getAnswerReaction();
        ReactionRequestDto reactionRequestDto3 = new ReactionRequestDto();
        reactionRequestDto3.setAnswerId(2L);
        reactionRequestDto3.setLiked(true);
        Mockito.when(reactionRepository.checkIfPresent(1L, "Aman")).thenReturn(false);
        Mockito.when(reactionRepository.save(reaction)).thenReturn(reaction);
        Mockito.when(reactionRepository.getLikesAndDislikes(reactionRequestDto3.getAnswerId(), true)).thenReturn(1L);
        Mockito.when(reactionRepository.getLikesAndDislikes(reactionRequestDto3.getAnswerId(), false)).thenReturn(0L);
        reactionService.createReaction(reaction.getUsername(), reactionRequestDto3);
    }
}