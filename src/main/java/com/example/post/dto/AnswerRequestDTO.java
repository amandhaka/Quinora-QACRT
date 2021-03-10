package com.example.post.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Data
public class AnswerRequestDTO {
    private String answerText;
    private String imgsrc;
    //private Datatype vid;
}
