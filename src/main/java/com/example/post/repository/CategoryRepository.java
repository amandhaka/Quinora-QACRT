package com.example.post.repository;

import com.example.post.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableMongoRepositories
public interface CategoryRepository  extends MongoRepository<Category, Long> {

//    String queryToFetchCategoryNames = "SELECT category.category_name FROM category";
//    @Query(value = queryToFetchCategoryNames, nativeQuery = true)
//    List<String> findAllCategoryNames();
}
