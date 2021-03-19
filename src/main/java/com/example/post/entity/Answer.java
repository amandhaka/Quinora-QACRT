package com.example.post.entity;


import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity(name = "answer")
@Data
public class Answer {
    @Id
    @GenericGenerator(name = "answer_id_seq", strategy = "increment")
    @GeneratedValue(generator = "answer_id_seq", strategy = GenerationType.AUTO)
    private Long Id;
    @Column(nullable = false)
    private Long questionID;
    @Column(nullable = false)
    private String userName;
    private Boolean status;
    private Date timeStamp;
    @Column(columnDefinition = "TEXT")
    private String imgsrc;
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String answerText;

}
