package com.example.post.utility;

import com.example.post.entity.AnswerReaction;
import lombok.Data;
@Data
public class ReactionUtility {
    public AnswerReaction answerReaction = null;
    public AnswerReaction getAnswerReaction() {
        answerReaction = new AnswerReaction();
        answerReaction.setUsername("Akshay");
        answerReaction.setAnswerId(1L);
        answerReaction.setReactionId(1L);
        answerReaction.setLiked(true);
        return answerReaction;
    }
    public AnswerReaction getAnswerReaction2() {
        answerReaction = new AnswerReaction();
        answerReaction.setUsername("Akshay");
        answerReaction.setAnswerId(1L);
        answerReaction.setReactionId(1L);
        answerReaction.setLiked(false);
        return answerReaction;
    }
}
