package com.example.post.repository;


import com.example.post.dto.CategoryRequestDto;
import com.example.post.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@EnableMongoRepositories
public interface QuestionRepository extends MongoRepository<Question, Long> {

    List<Question> findByUsername(String username);

}
