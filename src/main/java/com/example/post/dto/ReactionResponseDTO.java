package com.example.post.dto;

import lombok.Data;

@Data
public class ReactionResponseDTO {

    private Long answerId;

    private Long likesCount;

    private Long dislikesCount;
}
