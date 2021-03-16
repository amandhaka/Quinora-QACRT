package com.example.post.service.QuestionServiceImpl;


import com.example.post.dto.CategoryResponseDto;
import com.example.post.dto.QuestionRequestDto;
import com.example.post.dto.QuestionResponseDto;
import com.example.post.dto.QuestionStatus;
import com.example.post.entity.Category;
import com.example.post.entity.Question;
import com.example.post.repository.CategoryRepository;
import com.example.post.repository.QuestionRepository;
import com.example.post.service.ProducerService;
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

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProducerService producerService;

    @Override
    public List<QuestionResponseDto> getAllQuestions() {

        List<Question> questionList = questionRepository.findAll();
        List<QuestionResponseDto> questionResponseDtoList = new ArrayList<>();

        for(Question question: questionList) {
            if(question.isStatus()) {
                QuestionResponseDto questionResponseDto = new QuestionResponseDto();
                BeanUtils.copyProperties(question, questionResponseDto);
                questionResponseDtoList.add(questionResponseDto);
            }
        }
        return questionResponseDtoList;
    }

    @Override
    public QuestionResponseDto createQuestion(String username, QuestionRequestDto questionRequestDto) throws Exception {
        List<String> categoryList = categoryRepository.findAllCategoryNames();
        if(!categoryList.contains(questionRequestDto.getCategory())) throw new Exception("Category Not Found");
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        Question question = new Question();
        BeanUtils.copyProperties(questionRequestDto, question);
        question.setUsername(username);

        Question savedQuestion = questionRepository.save(question);
        BeanUtils.copyProperties(savedQuestion, questionResponseDto);
        producerService.sendMessageToSearchAfterUpdate(savedQuestion);
        return questionResponseDto;
    }

    @Override
    public QuestionResponseDto editQuestion(String username, Long questionId, QuestionRequestDto questionRequestDto) {

        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        System.out.println(optionalQuestion.get().getQuestionText());
        if(optionalQuestion.isPresent()){
            Question question = optionalQuestion.get();
            question.setQuestionTitle(questionRequestDto.getQuestionTitle());
            question.setQuestionText(questionRequestDto.getQuestionText());
            question.setCategory(questionRequestDto.getCategory());;
            BeanUtils.copyProperties(question, questionResponseDto);
            Question savedQuestion=questionRepository.save(question);
            producerService.sendMessageToSearchAfterUpdate(savedQuestion);
            return questionResponseDto;
        }
        return null;
    }

    @Override
    public List<QuestionResponseDto> questionListByCategory(String category) {
        List<QuestionResponseDto> responseDtoList = new ArrayList<>();
        List<Question> questionList = questionRepository.findByCategory(category);
        if(!questionList.isEmpty()){
            for(Question question: questionList) {
                if(question.isStatus()) {
                    QuestionResponseDto questionResponseDto = new QuestionResponseDto();
                    BeanUtils.copyProperties(question, questionResponseDto);
                    responseDtoList.add(questionResponseDto);
                }
            }
            return responseDtoList;
        }
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
        if(optionalQuestion.isPresent()) {
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
            //
            QuestionStatus questionStatus = new QuestionStatus();
            questionStatus.setQuestionId(questionId);
            //
            QuestionResponseDto questionResponseDto = new QuestionResponseDto();
            Question questionFromDb = optionalQuestion.get();
            questionFromDb.setStatus(false);
            BeanUtils.copyProperties(questionFromDb, questionResponseDto);
            questionRepository.save(questionFromDb);
            questionStatus.setStatus(false);
            producerService.updateQuestion(questionStatus);
            return questionResponseDto;
        }
        return null;
    }
    @Override
    public QuestionResponseDto questionByQuestionId(Long questionId) {
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        Optional<Question> question = questionRepository.findById(questionId);
        if(question.isPresent()){
            BeanUtils.copyProperties(question.get(),questionResponseDto);
            return questionResponseDto;
        }
        return null;
    }
}
