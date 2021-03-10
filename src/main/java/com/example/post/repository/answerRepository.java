package com.example.post.repository;

import com.example.post.entity.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface answerRepository extends CrudRepository<Answer, Long> {

    @Query(value = "SELECT answerId FROM answer WHERE answer_id = ?1", nativeQuery = true)
    boolean getAnswerById(Long answerId);
}
