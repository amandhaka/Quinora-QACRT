package com.example.post.dto;

import com.example.post.entity.Category;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

@Data
public class QuestionResponseDto {

    private Long questionId;
    private String questionTitle;
    private String questionText;
    private Date createdAt;
    private String username;
    private String category;
    private boolean status;
    private String questionImage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionResponseDto)) return false;
        QuestionResponseDto  that = (QuestionResponseDto) o;
        return status == that.status &&
                Objects.equals(questionId, that.questionId) &&
                Objects.equals(questionTitle, that.questionTitle) &&
                Objects.equals(questionText, that.questionText) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(username, that.username) &&
                Objects.equals(category, that.category) &&
                Objects.equals(questionImage, that.questionImage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(questionId, questionTitle, questionText, createdAt, username, category, status, questionImage);
    }
}
