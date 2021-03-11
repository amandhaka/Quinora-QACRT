package com.example.post.dto;

import com.example.post.entity.Comment;
import lombok.Data;
import sun.swing.StringUIClientPropertyKey;

import java.util.Date;
import java.util.List;

@Data
public class AnswerResponseDTO {
    private Long Id;
    private Long questionID;
    private String userName;
    private Boolean status;
    private Date timeStamp;
    private String imgsrc;
    private String answerText;
    private List<Comment> commentList;
    private Long likes;
    private Long dislikes;
    private String qtext;
    private String qtitle;

}
