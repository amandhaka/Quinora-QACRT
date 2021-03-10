package com.example.post.repository;

import com.example.post.entity.AnswerReaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReactionRepository extends CrudRepository<AnswerReaction, Long> {

    @Query (value = "select count(reaction.is_Liked) from reaction where is_Liked = ?2 and answer_id = ?1", nativeQuery = true)
    Long getLikesAndDislikes(Long answerId, boolean isLiked);

    @Query (value = "select case when exists ( select * from reaction where reaction.answer_id=?1 and reaction.username=?2)then 'TRUE' else 'FALSE' end", nativeQuery = true)
    Boolean checkIfPresent(Long answerId, String username);

    @Query (value = "select * from reaction where reaction.answer_id=?1 and reaction.username=?2", nativeQuery = true)
    AnswerReaction getUser(Long answerId, String username);

}
