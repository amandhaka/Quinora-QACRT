package com.example.post.dto;

import lombok.Data;

@Data
public class MailRequestDto {

    private String questionTitle;

    private String questionText;

    private String category;
}
