package com.example.post.repository;


import com.example.post.dto.CategoryRequestDto;
import com.example.post.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@EnableJpaRepositories
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByUsername(String username);

    List<Question> findByCategory(String category);
}
