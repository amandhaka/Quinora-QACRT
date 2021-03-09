package com.example.post.repository;

import com.example.post.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {

    String queryToFetchCategoryNames = "SELECT category.category_name FROM category";
    @Query(value = queryToFetchCategoryNames, nativeQuery = true)
    List<String> findAllCategoryNames();
}
