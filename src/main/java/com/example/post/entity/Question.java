package com.example.post.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

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
    @Column(columnDefinition = "TEXT")
    private String questionText;


    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;


    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String category;

    @Column(columnDefinition = "boolean default true")
    @NotNull
    private boolean status;

    @Column(columnDefinition = "TEXT")
    private String questionImage;

}
