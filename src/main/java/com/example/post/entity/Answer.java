package com.example.post.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Answer {
    @Id
    @GenericGenerator(name = "answer_id_seq", strategy = "increment")
    @GeneratedValue(generator = "answer_id_seq", strategy = GenerationType.AUTO)
    private Long answerId;

    @NotNull
    private Long questionId;

    @NotNull
    private String userName;
    private String text;
    private boolean status;
    private String timeStamp;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(referencedColumnName = "answerId", name = "answerId")
    private List<Comment> commentList;


}
