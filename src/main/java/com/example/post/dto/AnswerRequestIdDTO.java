package com.example.post.dto;

import lombok.Data;

@Data
public class AnswerRequestIdDTO {
    private Long answerId;
    private String answerText;
    private String imgsrc;
    //private Datatype vid;
}
