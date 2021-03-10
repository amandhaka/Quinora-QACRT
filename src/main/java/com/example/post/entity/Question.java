package com.example.post.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "question")
public class Question {
    @MongoId
    private Long questionId;

    @NotBlank
    @Size(min=10, max = 150)
    private String questionTitle;

    @NotBlank
    @Size(min = 30, max = 400)
    private String questionText;


    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    private String username;
    private List<Category> categoryList;

    @Column(columnDefinition = "boolean default true")
    @NotNull
    private boolean status;

}
