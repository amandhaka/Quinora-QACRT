package com.example.post.service.QuestionServiceImpl;

import com.example.post.dto.QuestionRequestDto;
import com.example.post.dto.QuestionResponseDto;
import com.example.post.entity.Question;
import com.example.post.repository.QuestionRepository;
import com.example.post.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<QuestionResponseDto> getAllQuestions() {

        List<Question> questionList = questionRepository.findAll();
        List<QuestionResponseDto> questionResponseDtoList = new ArrayList<>();

        for(Question question: questionList) {
            if(question.getStatus()) {
                QuestionResponseDto questionResponseDto = new QuestionResponseDto();
                BeanUtils.copyProperties(question, questionResponseDto);
                questionResponseDtoList.add(questionResponseDto);
            }
        }
        return questionResponseDtoList;
    }

    @Override
    public QuestionResponseDto createQuestion(String username, QuestionRequestDto questionRequestDto) {
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        Question question = new Question();
        BeanUtils.copyProperties(questionRequestDto, question);
        Question savedQuestion = questionRepository.save(question);
        BeanUtils.copyProperties(savedQuestion, questionResponseDto);
        return questionResponseDto;
    }

    @Override
    public QuestionResponseDto editQuestion(String username, Long questionId, QuestionRequestDto questionRequestDto) {

        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if(optionalQuestion.isPresent()){
            Question question = optionalQuestion.get();
            question.setQuestionTitle(questionRequestDto.getQuestionTitle());
            question.setQuestionText(questionRequestDto.getQuestionText());
            question.setCategoryList(questionRequestDto.getCategoryList());
            question.setUsername(questionRequestDto.getUsername());
            BeanUtils.copyProperties(question, questionResponseDto);
            return questionResponseDto;
        }
        return null;
    }

    @Override
    public List<QuestionResponseDto> questionListByCategory(Long categoryId) {
        return null;
    }

    @Override
    public List<QuestionResponseDto> questionListByUsername(String username) {
        List<QuestionResponseDto> questionResponseDtoList = new ArrayList<>();
        List<Question> questionList = questionRepository.findByUsername(username);
        if(questionList.size()!=0) {
            for (Question question : questionList) {
                QuestionResponseDto questionResponseDto = new QuestionResponseDto();
                BeanUtils.copyProperties(question, questionResponseDto);
                questionResponseDtoList.add(questionResponseDto);
            }
            return questionResponseDtoList;
        }
        return null;
    }

    @Override
    public QuestionResponseDto deleteQuestionById(String username, Long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if(optionalQuestion.isPresent() && optionalQuestion.get().getStatus()) {
            Question questionFromDb = optionalQuestion.get();
            QuestionResponseDto questionResponseDto = new QuestionResponseDto();
            BeanUtils.copyProperties(questionFromDb, questionResponseDto);
            questionRepository.deleteById(questionId);
            return questionResponseDto;
        }
        return null;
    }

    @Override
    public QuestionResponseDto disableQuestionById(String username, Long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if(optionalQuestion.isPresent()) {
            QuestionResponseDto questionResponseDto = new QuestionResponseDto();
            Question questionFromDb = optionalQuestion.get();
            questionFromDb.setStatus(false);
            BeanUtils.copyProperties(questionFromDb, questionResponseDto);
            return questionResponseDto;
        }
        return null;
    }
}
