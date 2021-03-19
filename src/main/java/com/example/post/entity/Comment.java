package com.example.post.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "Comment")
@Data
public class Comment {
    @Id
    @GenericGenerator(name = "comment_id_seq", strategy = "increment")
    @GeneratedValue(generator = "comment_id_seq", strategy = GenerationType.AUTO)
    private Long commentId;

    @CreatedDate
    @Column(name = "time_stamp",nullable = false, updatable = false)
    private Date timeStamp;
    private boolean status;
    private String commentText;
    @Column(nullable = false)
    private Long answerId;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "answerList")
//    private Answer answer;

    @Column(nullable = false)
    private String userName;
}
