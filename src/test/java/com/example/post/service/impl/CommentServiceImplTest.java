package com.example.post.service.impl;

import com.example.post.dto.commentResponseDTO;
import com.example.post.dto.deleteCommentRequestDTO;
import com.example.post.dto.postCommentRequestDTO;
import com.example.post.dto.updateCommentRequestDTO;
import com.example.post.entity.Comment;
import com.example.post.repository.commentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
class CommentServiceImplTest {
    @InjectMocks
    private commentServiceImpl commentService;
    @Mock
    private commentRepository commentRepository;
    @BeforeEach
    public void init() { MockitoAnnotations.openMocks(this);
        System.out.println("before");}
    @AfterEach
    public void teardown() {
        System.out.println("after");
    }
    @Test
    void getCommentsByAnswerId() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> mockObject = objectMapper.readValue(new URL("file:src/test/resources/comment.mock"), ArrayList.class);
        ArrayList<Comment> comments = new ArrayList<>();
        for(Map<String, Object> obj: mockObject) {
            Comment comment = new Comment();
            comment.setStatus((boolean)obj.get("status"));
            comment.setCommentText((String)obj.get("commentText"));
            comment.setAnswerId(Long.parseLong(obj.get("answerId") + ""));
            comment.setUserName((String)obj.get("userName"));
            comments.add(comment);
        }
        Mockito.when(commentRepository.getCommentsByAnsId(1L)).thenReturn(comments);
        List<commentResponseDTO> commentResponseDTOList = commentService.getCommentsByAnswerId(1L);
        assertEquals(commentResponseDTOList.size(), 2);
        Mockito.when(commentRepository.getCommentsByAnsId(2L)).thenThrow(NullPointerException.class);
        commentResponseDTOList = commentService.getCommentsByAnswerId(2L);
        assertEquals(commentResponseDTOList, null);
        Mockito.verify(commentRepository).getCommentsByAnsId(1L);
        Mockito.verify(commentRepository).getCommentsByAnsId(2L);
    }
    @Test
    void postCommentByUsername() throws Exception {
        Comment mockComment = new Comment();
        mockComment.setUserName("preetham");
        mockComment.setAnswerId(1L);
        mockComment.setCommentText("heaaallio");
        mockComment.setStatus(true);
        mockComment.setTimeStamp(new Date());
        mockComment.setCommentId(1L);

        postCommentRequestDTO postCommentRequestDTO = new postCommentRequestDTO();
        postCommentRequestDTO.setAnswerId(1L);
        postCommentRequestDTO.setDate(new Date());
        postCommentRequestDTO.setText("heaaallio");
        Mockito.when(commentRepository.save(any(Comment.class))).thenReturn(mockComment);
        commentResponseDTO responseDTO = commentService.postCommentByUserName(postCommentRequestDTO, "preetham");
        assertEquals(responseDTO.getCommentId(), mockComment.getCommentId());
//        Mockito.when(commentRepository.save(any(Comment.class))).thenThrow(NullPointerException.class);
        mockComment.setUserName(null);
        mockComment.setAnswerId(1L);
        mockComment.setCommentText("heaaallio");
        mockComment.setStatus(true);
        mockComment.setTimeStamp(new Date());
        mockComment.setCommentId(1L);
//        Mockito.when(commentRepository.save(mockComment)).thenThrow(NullPointerException.class);
        responseDTO = commentService.postCommentByUserName(postCommentRequestDTO, null);
        assertEquals(responseDTO, null);
        Mockito.verify(commentRepository).save(any(Comment.class));
//        Mockito.verify(commentRepository).save(mockComment);
    }
    @Test
    void updateCommentByCommentId() throws Exception {
        Comment mockComment = new Comment();
        mockComment.setUserName("preetham");
        mockComment.setAnswerId(1L);
        mockComment.setCommentText("heaaallio");
        mockComment.setStatus(true);
        mockComment.setTimeStamp(new Date());
        mockComment.setCommentId(1L);
        updateCommentRequestDTO requestDTO = new updateCommentRequestDTO();
        requestDTO.setCommentId(1L);
        requestDTO.setDate(new Date());
        requestDTO.setNewCommentText("new text");
        Mockito.when(commentRepository.findById(1L)).thenReturn(Optional.of(mockComment));
        Mockito.when(commentRepository.save(any(Comment.class))).thenReturn(mockComment);
        commentResponseDTO responseDTO = commentService.updateCommentByCommentId(requestDTO, "preetham");
        assertEquals(responseDTO.getCommentId(), 1L);
        requestDTO.setCommentId(2L);
        Mockito.when(commentRepository.findById(2L)).thenThrow(NullPointerException.class);
        responseDTO = commentService.updateCommentByCommentId(requestDTO, "preetham");
        assertEquals(responseDTO, null);
        Mockito.verify(commentRepository).findById(1L);
        Mockito.verify(commentRepository).save(any(Comment.class));
    }
    @Test
    void deleteCommentByCommentId() throws Exception {
        Comment mockComment = new Comment();
        mockComment.setUserName("preetham");
        mockComment.setAnswerId(1L);
        mockComment.setCommentText("heaaallio");
        mockComment.setStatus(true);
        mockComment.setTimeStamp(new Date());
        mockComment.setCommentId(1L);
        deleteCommentRequestDTO requestDTO = new deleteCommentRequestDTO();
        requestDTO.setCommentId(1L);
        requestDTO.setDate(new Date());
        Mockito.when(commentRepository.findById(1L)).thenReturn(Optional.of(mockComment));
        Mockito.when(commentRepository.save(any(Comment.class))).thenReturn(mockComment);
        commentResponseDTO responseDTO = commentService.deleteCommentByCommentId(requestDTO, "preetham");
        assertEquals(responseDTO.getCommentId(), 1L);
        requestDTO.setCommentId(2L);
        Mockito.when(commentRepository.findById(2L)).thenThrow(NullPointerException.class);
        responseDTO = commentService.deleteCommentByCommentId(requestDTO, "preetham");
        assertEquals(responseDTO, null);
        Mockito.verify(commentRepository).findById(1L);
        Mockito.verify(commentRepository).save(any(Comment.class));
    }
    @Test
    void getCommentsSortedByDate() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> mockObject = objectMapper.readValue(new URL("file:src/test/resources/comment.mock"), ArrayList.class);
        ArrayList<Comment> comments = new ArrayList<>();
        for(Map<String, Object> obj: mockObject) {
            Comment comment = new Comment();
            comment.setStatus((boolean)obj.get("status"));
            comment.setCommentText((String)obj.get("commentText"));
            comment.setAnswerId(Long.parseLong(obj.get("answerId") + ""));
            comment.setUserName((String)obj.get("userName"));
            comments.add(comment);
        }
        Mockito.when(commentRepository.getSortedCommentsByAnsId(1L)).thenReturn(comments);
        List<commentResponseDTO> commentResponseDTOList = commentService.getCommentsSortedByDate(1L);
        assertEquals(commentResponseDTOList.size(), 2);
        Mockito.when(commentRepository.getSortedCommentsByAnsId(2L)).thenThrow(NullPointerException.class);
        commentResponseDTOList = commentService.getCommentsSortedByDate(2L);
        assertEquals(commentResponseDTOList, null);
        Mockito.verify(commentRepository).getSortedCommentsByAnsId(1L);
        Mockito.verify(commentRepository).getSortedCommentsByAnsId(2L);
    }
}