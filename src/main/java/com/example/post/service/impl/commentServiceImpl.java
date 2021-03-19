package com.example.post.service.impl;

import com.example.post.dto.commentResponseDTO;
import com.example.post.dto.deleteCommentRequestDTO;
import com.example.post.dto.postCommentRequestDTO;
import com.example.post.dto.updateCommentRequestDTO;
import com.example.post.entity.Comment;
import com.example.post.repository.commentRepository;
import com.example.post.service.commentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class commentServiceImpl implements commentService {
    @Autowired
    private commentRepository commentRepository;
    //    @Autowired
//    private answerRepository answerRepository;
    @Override
    public List<commentResponseDTO> getCommentsByAnswerId(Long answerId) {
        try{
            List<Comment> commentList = commentRepository.getCommentsByAnsId(answerId);
            List<commentResponseDTO> commentResponseDTOList = new ArrayList<>();
            for(Comment comments: commentList) {
                commentResponseDTO responseDTO = new commentResponseDTO();
                BeanUtils.copyProperties(comments, responseDTO);
                commentResponseDTOList.add(responseDTO);
            }
            return commentResponseDTOList;
        } catch (NullPointerException e) {
            return null;
        }
    }
    @Override
    public commentResponseDTO postCommentByUserName(postCommentRequestDTO postCommentRequestDTO, String userName) throws Exception {
        if(userName == null) {
            return null;
        }
        Comment newComment = new Comment();
        //if(!answerRepository.getAnswerById(postCommentRequestDTO.getAnswerId())) throw new Exception("Answer does not exist");
//        Answer theAnswer = commentRepository.getAnswerByAnswerId(postCommentRequestDTO.getAnswerId());
        newComment.setCommentText(postCommentRequestDTO.getText());
        newComment.setStatus(true);
        newComment.setUserName(userName);
        newComment.setAnswerId(postCommentRequestDTO.getAnswerId());
        newComment.setTimeStamp(postCommentRequestDTO.getDate());
        Comment savedComment = commentRepository.save(newComment);
        commentResponseDTO responseDTO = new commentResponseDTO();
//        BeanUtils.copyProperties(newComment, responseDTO);
        BeanUtils.copyProperties(savedComment, responseDTO);
        return responseDTO;
    }
    @Override
    public commentResponseDTO updateCommentByCommentId(updateCommentRequestDTO updateCommentRequestDTO, String userName) {
        Optional<Comment> oldComment = Optional.of(new Comment());
        try{
            oldComment = commentRepository.findById(updateCommentRequestDTO.getCommentId());
        }catch (NullPointerException e) {
            return null;
        }
        if(oldComment.isPresent()) {
            oldComment.get().setCommentText(updateCommentRequestDTO.getNewCommentText());
        }
        commentRepository.save(oldComment.get());
        commentResponseDTO responseDTO = new commentResponseDTO();
        BeanUtils.copyProperties(oldComment.get(), responseDTO);
        return responseDTO;
    }
    @Override
    public commentResponseDTO deleteCommentByCommentId(deleteCommentRequestDTO deleteRequestDTO, String userName) {
        Optional<Comment> comment = Optional.of(new Comment());
        try{
            comment = commentRepository.findById(deleteRequestDTO.getCommentId());
        }catch (NullPointerException e) {
            return null;
        }
        if(comment.isPresent()) {
            comment.get().setStatus(false);
        }
        commentRepository.save(comment.get());
        commentResponseDTO responseDTO = new commentResponseDTO();
        BeanUtils.copyProperties(comment.get(), responseDTO);
        return responseDTO;
    }
    @Override
    public List<commentResponseDTO> getCommentsSortedByDate(Long answerId) {
        try {
            List<Comment> commentList = commentRepository.getSortedCommentsByAnsId(answerId);
            List<commentResponseDTO> commentResponseDTOList = new ArrayList<>();
            for (Comment comments : commentList) {
                commentResponseDTO responseDTO = new commentResponseDTO();
                BeanUtils.copyProperties(comments, responseDTO);
                commentResponseDTOList.add(responseDTO);
            }
            return commentResponseDTOList;
        } catch (NullPointerException e) {
            return null;
        }
    }
}