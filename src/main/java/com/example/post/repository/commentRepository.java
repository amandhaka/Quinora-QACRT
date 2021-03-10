package com.example.post.repository;

import com.example.post.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface commentRepository extends CrudRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comment WHERE comment.answer_Id = ?1 and comment.status = true", nativeQuery = true)
    List<Comment> getCommentsByAnsId(Long answerId);

    @Query(value = "SELECT * FROM comment WHERE comment.answer_Id = ?1 and comment.status = true ORDER BY time_stamp DESC", nativeQuery = true)
    List<Comment> getSortedCommentsByAnsId(Long answerId);
}
