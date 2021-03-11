package com.example.post.repository;

import com.example.post.dto.ReactionLikesAndDislikesResponseDto;
import com.example.post.dto.ReactionResponseDTO;
import com.example.post.entity.AnswerReaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReactionRepository extends CrudRepository<AnswerReaction, Long> {

    @Query (value = "select count(reaction.is_Liked) from reaction where is_Liked = ?2 and answer_id = ?1", nativeQuery = true)
    Long getLikesAndDislikes(Long answerId, boolean isLiked);

    @Query (value = "select case when exists ( select * from reaction where reaction.answer_id=?1 and reaction.username=?2)then 'TRUE' else 'FALSE' end", nativeQuery = true)
    Boolean checkIfPresent(Long answerId, String username);

    @Query (value = "select * from reaction where reaction.answer_id=?1 and reaction.username=?2", nativeQuery = true)
    AnswerReaction getUser(Long answerId, String username);

    String queryToGetNumberOfDisLikes = "select count(nullif(r1.is_liked = false,false)) from reaction r1 where r1.reaction_id in(select r.reaction_id from  answer a full join reaction r on a.id = r.answer_id where a.user_name = ?1)";
    @Query(value = queryToGetNumberOfDisLikes, nativeQuery = true)
    Long findNumberOfDislikes(String username);

    String queryToGetNumberOfLikes = "select count(nullif(r1.is_liked = false,true)) from reaction r1 where r1.reaction_id in(select r.reaction_id from  answer a full join reaction r on a.id = r.answer_id where a.user_name = ?1)";
    @Query(value = queryToGetNumberOfLikes, nativeQuery = true)
    Long findNumberOfLikes(String username);

}
