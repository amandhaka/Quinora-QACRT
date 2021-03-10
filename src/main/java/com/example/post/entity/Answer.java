package com.example.post.entity;


import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity
@Data
public class Answer {
    @Id
    @GenericGenerator(name = "answer_id_seq", strategy = "increment")
    @GeneratedValue(generator = "answer_id_seq", strategy = GenerationType.AUTO)
    private Long Id;
    private Long questionID;
    private String userName;
    private Boolean status;
    private Date timeStamp;
    private String imgsrc;
    //private Long multimediaId;
    private String answerText;

}
