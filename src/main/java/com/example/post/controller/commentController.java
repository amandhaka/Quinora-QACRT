package com.example.post.controller;

import com.example.post.dto.commentResponseDTO;
import com.example.post.dto.deleteCommentRequestDTO;
import com.example.post.dto.postCommentRequestDTO;
import com.example.post.dto.updateCommentRequestDTO;
import com.example.post.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class commentController {

    @Autowired
    private commentService commentService;

    @GetMapping("/getComments/{answerId}")
    public List<commentResponseDTO> getCommentsByAnswerId(@PathVariable("answerId") Long answerId) {
        return commentService.getCommentsByAnswerId(answerId);
    }

    @PostMapping("/postComment/{userName}")
    public commentResponseDTO postCommentByUserName(@RequestBody postCommentRequestDTO postCommentRequestDTO, @PathVariable("userName") String userName) throws Exception {
        return commentService.postCommentByUserName(postCommentRequestDTO, userName);
    }

    @PutMapping("/updateComment/{userName}")
    public commentResponseDTO updateCommentByCommentId(@RequestBody updateCommentRequestDTO updateCommentRequestDTO, @PathVariable("userName") String userName) {
        return commentService.updateCommentByCommentId(updateCommentRequestDTO, userName);
    }

    @PutMapping("/deleteComment/{userName}")
    public commentResponseDTO deleteCommentByCommentId(@RequestBody deleteCommentRequestDTO deleteRequestDTO, @PathVariable("userName") String userName) {
        return commentService.deleteCommentByCommentId(deleteRequestDTO, userName);
    }

    @GetMapping("/sortCommentsByDate/{answerId}")
    public List<commentResponseDTO> getCommentsSortedByDate(@PathVariable("answerId") Long answerId) {
        return commentService.getCommentsSortedByDate(answerId);
    }

}
