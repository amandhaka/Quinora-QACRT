package com.example.post.service.impl;

import com.example.post.dto.QuestionRequestDto;
import com.example.post.dto.QuestionResponseDto;
import com.example.post.entity.Category;
import com.example.post.entity.Question;
import com.example.post.repository.CategoryRepository;
import com.example.post.repository.QuestionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RunWith(MockitoJUnitRunner.class)
class QuestionServiceTest {

    @InjectMocks
    private QuestionServiceImpl questionService;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Autowired
    private QuestionRepository questionRepositoryDb;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    public void teardown(){

    }
    @Test
    public void getAllQuestions() throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String,Object>> mockObject = objectMapper.readValue(
                new URL("file:src/test/resources/question.mock"),ArrayList.class);


        List<Question> list = new ArrayList<>();

        for(Map<String,Object> obj: mockObject) {
            Question question =new Question();
            question.setUsername((String)obj.get("username"));
            question.setCategory((String)obj.get("category"));
            question.setQuestionImage((String)obj.get("questionImage"));
            question.setQuestionText((String)obj.get("questionText"));
            question.setQuestionTitle((String)obj.get("questionTitle"));
            question.setStatus((boolean)obj.get("status"));
            question.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(((String)(obj.get("createdAt"))).substring(0,19)));
            question.setQuestionId(Long.parseLong(String.valueOf(obj.get("questionId"))));
            list.add(question);
        }

        Mockito.when(questionRepository.findAll()).thenReturn(list);
        List<QuestionResponseDto> questionResponseDtoList = questionService.getAllQuestions();
        assertEquals(questionResponseDtoList.size(),2);

        Mockito.when(questionRepository.findAll()).thenThrow(NullPointerException.class);
        List<QuestionResponseDto> questionResponseDto = questionService.getAllQuestions();
        assertEquals(questionResponseDto,null);
    }

    @Test
    public void questionListByUsername() {
        List<Question> list = new ArrayList<>();
        Question question = new Question();
        question.setQuestionId(1l);
        question.setCreatedAt(new Date());
        question.setStatus(true);
        question.setQuestionImage("");
        question.setUsername("Aman");
        question.setQuestionTitle("");
        question.setQuestionText("");
        question.setCategory("Movies");
        list.add(question);
        Mockito.when(questionRepository.findByUsername("aman")).thenReturn(list);
        assertEquals(questionService.questionListByUsername("aman").get(0).getCategory(), question.getCategory());
        Mockito.when(questionRepository.findByUsername(null)).thenThrow(NullPointerException.class);
        assertEquals(questionService.questionListByUsername(null), null);
    }

    @Test
    public void questionByQuestionId() {
        Question question = new Question();
        question.setQuestionId(1l);
        question.setCreatedAt(new Date());
        question.setStatus(true);
        question.setQuestionImage("");
        question.setUsername("Aman");
        question.setQuestionTitle("");
        question.setQuestionText("");
        question.setCategory("Movies");
        Mockito.when(questionRepository.findById(1l)).thenReturn(Optional.ofNullable(question));
        assertEquals(questionService.questionByQuestionId(1l).getCategory(), question.getCategory());
        Mockito.when(questionRepository.findByUsername(null)).thenThrow(NullPointerException.class);
        assertEquals(questionService.questionListByUsername(null), null);
    }

    @Test
    public void createQuestion() throws Exception {
        Question question = new Question();
        question.setCategory("Movies");
        question.setQuestionTitle("This is the question Title");
        question.setUsername("aman");
        question.setQuestionText("This is question Text");
        question.setQuestionImage("");
        question.setQuestionId(1l);
        question.setStatus(true);
        question.setCreatedAt(new Date());
        String username = "aman";
        Mockito.when(questionRepository.save(any(Question.class))).thenReturn(question);
        QuestionRequestDto questionRequestDto = new QuestionRequestDto();
        questionRequestDto.setCategory("Movies");
        questionRequestDto.setQuestionTitle("This is the question Title");
        questionRequestDto.setQuestionText("This is question Text");
        questionRequestDto.setQuestionImage("");
        questionRequestDto.setStatus(true);
        questionRequestDto.setCreatedAt(new Date());

        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setCategory("Movies");
        questionResponseDto.setQuestionTitle("This is the question Title");
        questionResponseDto.setUsername("aman");
        questionResponseDto.setQuestionText("This is question Text");
        questionResponseDto.setQuestionImage("");
        questionResponseDto.setQuestionId(1l);
        questionResponseDto.setStatus(true);
        questionResponseDto.setCreatedAt(new Date());
        assertEquals(questionService.createQuestion(username, questionRequestDto).toString(),questionResponseDto.toString());

    }

    @Test
    public void editQuestion() {
        Question question = new Question();
        question.setCategory("Movies");
        question.setQuestionTitle("This is the question Title");
        question.setUsername("aman");
        question.setQuestionText("This is question Text");
        question.setQuestionImage("");
        question.setQuestionId(1l);
        question.setStatus(true);
        question.setCreatedAt(new Date());
        String username = "aman";

        QuestionRequestDto questionRequestDto = new QuestionRequestDto();
        questionRequestDto.setCategory("Movies");
        questionRequestDto.setQuestionTitle("This is the question Title");
        questionRequestDto.setQuestionText("This is question Text");
        questionRequestDto.setQuestionImage("");
        questionRequestDto.setStatus(true);
        questionRequestDto.setCreatedAt(new Date());

        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setCategory("Movies");
        questionResponseDto.setQuestionTitle("This is the question Title");
        questionResponseDto.setUsername("aman");
        questionResponseDto.setQuestionText("This is question Text");
        questionResponseDto.setQuestionImage("");
        questionResponseDto.setQuestionId(1l);
        questionResponseDto.setStatus(true);
        questionResponseDto.setCreatedAt(new Date());

        Mockito.when(questionRepository.save(any(Question.class))).thenReturn(question);
        Mockito.when(questionRepository.findById(1l)).thenReturn(Optional.of(question));
        assertEquals(questionService.editQuestion(username,1l, questionRequestDto).toString(),questionResponseDto.toString());

    }

    @Test
    public void questionListByCategory() throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String,Object>> mockObject = objectMapper.readValue(
                new URL("file:src/test/resources/question.mock"),ArrayList.class);
        List<Question> list = new ArrayList<>();
        for(Map<String,Object> obj: mockObject) {
            Question question =new Question();
            question.setUsername((String)obj.get("username"));
            question.setCategory((String)obj.get("category"));
            question.setQuestionImage((String)obj.get("questionImage"));
            question.setQuestionText((String)obj.get("questionText"));
            question.setQuestionTitle((String)obj.get("questionTitle"));
            question.setStatus((boolean)obj.get("status"));
            question.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(((String)(obj.get("createdAt"))).substring(0,19)));
            question.setQuestionId(Long.parseLong(String.valueOf(obj.get("questionId"))));
            list.add(question);
        }
        Mockito.when(questionRepository.findByCategory("Movies")).thenReturn(list);
        assertEquals(questionService.questionListByCategory("Movies").size(), 2);
        Mockito.when(questionRepository.findByCategory("Movies")).thenThrow(NullPointerException.class);
        assertThrows(NullPointerException.class, (Executable) questionService.questionListByCategory("Movies"));
    }

    @Test
    public void deleteQuestionById() {
        Question question = new Question();
        question.setCategory("Movies");
        question.setQuestionTitle("This is the question Title");
        question.setUsername("aman");
        question.setQuestionText("This is question Text");
        question.setQuestionImage("");
        question.setQuestionId(1l);
        question.setStatus(true);
        question.setCreatedAt(new Date());
        String username = "aman";
        Mockito.when(questionRepository.findById(1l)).thenReturn(Optional.of(question));
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setCategory("Movies");
        questionResponseDto.setQuestionTitle("This is the question Title");
        questionResponseDto.setUsername("aman");
        questionResponseDto.setQuestionText("This is question Text");
        questionResponseDto.setQuestionImage("");
        questionResponseDto.setQuestionId(1l);
        questionResponseDto.setStatus(true);
        questionResponseDto.setCreatedAt(new Date());
        assertEquals(questionService.deleteQuestionById("aman",1l).getCategory(),questionResponseDto.getCategory());
        Mockito.when(questionRepository.findById(1l)).thenThrow(NullPointerException.class);
        assertEquals(questionService.deleteQuestionById("aman",1l),null);
    }

    @Test
    public void disableQuestionById() {
        Question question = new Question();
        question.setCategory("Movies");
        question.setQuestionTitle("This is the question Title");
        question.setUsername("aman");
        question.setQuestionText("This is question Text");
        question.setQuestionImage("");
        question.setQuestionId(1l);
        question.setStatus(true);
        question.setCreatedAt(new Date());
        String username = "aman";
        Mockito.when(questionRepository.findById(1l)).thenReturn(Optional.of(question));
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setCategory("Movies");
        questionResponseDto.setQuestionTitle("This is the question Title");
        questionResponseDto.setUsername("aman");
        questionResponseDto.setQuestionText("This is question Text");
        questionResponseDto.setQuestionImage("");
        questionResponseDto.setQuestionId(1l);
        questionResponseDto.setStatus(true);
        questionResponseDto.setCreatedAt(new Date());
        assertEquals(questionService.disableQuestionById("aman",1l).getCategory(),questionResponseDto.getCategory());
        Mockito.when(questionRepository.findById(1l)).thenThrow(NullPointerException.class);
        assertEquals(questionService.disableQuestionById("aman",1l),null);
    }

    @Test
    public void findAllCategory() {
        List<Category> categoryList = new ArrayList<>();
        Category category = new Category();
        category.setCategoryId(1l);
        category.setCategoryName("Movies");
        categoryList.add(category);
        Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);
        assertEquals(categoryService.findAllCategory().size(), 1);
    }

}
