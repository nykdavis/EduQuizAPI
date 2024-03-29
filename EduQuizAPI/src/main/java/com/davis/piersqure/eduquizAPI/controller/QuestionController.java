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
import com.davis.piersqure.eduquizAPI.exception.BadRequestException;
import com.davis.piersqure.eduquizAPI.exception.NotFoundException;
import com.davis.piersqure.eduquizAPI.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/questions")
@Slf4j
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@GetMapping
	public List<Question> getAllQuestions() {
		log.info("Fetching all questions.");
		List<Question> questions = questionService.getAllQuestions();
		if (questions.isEmpty()) {
			throw new NotFoundException("No questions found.");
		}
		log.info("Retrieved {} questions.", questions.size());
		return questions;
	}

	@PostMapping
	public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
		try {
			log.info("Creating a new question: {}", question);
			Question createdQuestion = questionService.createQuestion(question);
			log.info("Question created: {}", createdQuestion);
			return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
		} catch (BadRequestException ex) {
			log.error("Bad request: {}", ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception ex) {
			log.error("Internal server error: {}", ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{questionId}")
	public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
		log.info("Deleting question with ID: {}", questionId);
		questionService.deleteQuestionWithAnswers(questionId);
		log.info("Question with ID {} deleted successfully.", questionId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/generateQuestionSet")
	public ResponseEntity<List<QuestionDto>> generateQuestionSet() {
		log.info("Generating question set.");
		List<QuestionDto> questionSet = questionService.generateQuestionSet();
		log.info("Question set generated successfully.");
		return new ResponseEntity<>(questionSet, HttpStatus.OK);
	}

}
