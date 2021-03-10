package com.example.post.dto;

import lombok.Data;

@Data
public class ReactionRequestDto {

    private Long answerId;

    private boolean isLiked;
}
