package com.example.post.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Answer {
    @Id
    @GenericGenerator(name = "answer_id_seq", strategy = "increment")
    @GeneratedValue(generator = "answer_id_seq", strategy = GenerationType.AUTO)
    private Long answerId;

    private Long questionId;
    private String userName;
    private String text;
    private boolean status;
    private String timeStamp;

//    @OneToMany
//    private List<Comment> commentList;
//    @JoinColumn(referencedColumnName = "answerId", name = "answerId")

}
