package com.example.post.dto;

import lombok.Data;

@Data
public class AnswerStatus {

    private Long Id;
    private Long questionID;
    private Boolean status;
}
