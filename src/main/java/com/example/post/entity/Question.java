package com.example.post.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "question")
public class Question {
    @Id
    @GenericGenerator(name = "question_id_seq", strategy = "increment")
    @GeneratedValue(generator = "question_id_seq", strategy = GenerationType.AUTO)
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


    @OneToMany
    List<Category> categoryList;

    @OneToMany
    List<Answer> answersList;

    private String username;

    private Boolean status;
}
