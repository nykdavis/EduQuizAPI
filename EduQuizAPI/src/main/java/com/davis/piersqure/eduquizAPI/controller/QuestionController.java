package com.davis.piersqure.eduquizAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davis.piersqure.eduquizAPI.dto.QuestionDto;
import com.davis.piersqure.eduquizAPI.entity.Question;
import com.davis.piersqure.eduquizAPI.service.QuestionService;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
	


    @Autowired
    private QuestionService questionService;

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question createdQuestion = questionService.createQuestion(question);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestionWithAnswers(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/generateQuestionSet")
    public ResponseEntity<List<QuestionDto>> generateQuestionSet() {
        List<QuestionDto> questionSet = questionService.generateQuestionSet();
        return new ResponseEntity<>(questionSet, HttpStatus.OK);
    }

}
