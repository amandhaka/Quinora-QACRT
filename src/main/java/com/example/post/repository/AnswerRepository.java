package com.example.post.repository;

import com.example.post.entity.Answer;
import com.example.post.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer, Long> {

    @Query (value = "select * from Answer where answer.questionid = ?1 and answer.status = true", nativeQuery = true)
    List<Answer> answersOnQuestion (Long quid);


}
