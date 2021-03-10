package com.example.post.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Document(collection = "category")
@Data
public class Category {
    @MongoId
    private Long categoryId;

    @NotBlank
    private String categoryName;

}
