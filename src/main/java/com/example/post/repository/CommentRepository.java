package com.example.post.repository;

import com.example.post.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Query(value = "select * from Comment where comment.answer_id=?1 and comment.status = true", nativeQuery = true)
    List<Comment> commentsOnAnswer (Long answerId);
}
