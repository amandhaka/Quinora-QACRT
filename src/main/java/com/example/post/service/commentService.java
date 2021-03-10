package com.example.post.service;

import com.example.post.dto.commentResponseDTO;
import com.example.post.dto.deleteCommentRequestDTO;
import com.example.post.dto.postCommentRequestDTO;
import com.example.post.dto.updateCommentRequestDTO;

import java.util.List;

public interface commentService {
    List<commentResponseDTO> getCommentsByAnswerId(Long answerId);

    commentResponseDTO postCommentByUserName(postCommentRequestDTO postCommentRequestDTO,String userName) throws Exception;

    commentResponseDTO updateCommentByCommentId(updateCommentRequestDTO updateCommentRequestDTO, String userName);

    commentResponseDTO deleteCommentByCommentId(deleteCommentRequestDTO deleteRequestDTO, String userName);

    List<commentResponseDTO> getCommentsSortedByDate(Long answerId);
}
