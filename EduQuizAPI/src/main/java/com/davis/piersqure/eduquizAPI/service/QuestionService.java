package com.davis.piersqure.eduquizAPI.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davis.piersqure.eduquizAPI.dto.AnswerDto;
import com.davis.piersqure.eduquizAPI.dto.QuestionDto;
import com.davis.piersqure.eduquizAPI.entity.Answer;
import com.davis.piersqure.eduquizAPI.entity.Question;
import com.davis.piersqure.eduquizAPI.repository.AnswerRepository;
import com.davis.piersqure.eduquizAPI.repository.QuestionRepository;

import jakarta.transaction.Transactional;

@Service
public class QuestionService {
	
	@Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Transactional
    public Question createQuestion(Question question) {
        // Save question and its answers
        Question savedQuestion = questionRepository.save(question);
        for (Answer answer : savedQuestion.getAnswers()) {
            answer.setQuestion(savedQuestion);
            answerRepository.save(answer);
        }
        return savedQuestion;
    }
    
    @Transactional
    public void deleteQuestionWithAnswers(Long questionId) {
        Question question = questionRepository.findById(questionId).orElse(null);
        if (question != null) {
            questionRepository.delete(question);
        }
    }
    
    public List<QuestionDto> generateQuestionSet() {
        List<QuestionDto> questionSet = new ArrayList<>();

        // Get distinct chapters
        List<String> chapters = questionRepository.findDistinctChapters();

        // Fetch one question from each chapter
        for (String chapter : chapters) {
            Question question = questionRepository.findRandomByChapter(chapter);
            if (question != null) {
                // Convert Question entity to QuestionDto
                QuestionDto questionDto = new QuestionDto();
                questionDto.setId(question.getId());
                questionDto.setQuestionText(question.getQuestionText());

                // Exclude the correct value from answers
                List<Answer> answers = question.getAnswers();
                List<AnswerDto> answerDtos = new ArrayList<>();
                for (Answer answer : answers) {
                    AnswerDto answerDto = new AnswerDto();
                    answerDto.setId(answer.getId());
                    answerDto.setAnswerText(answer.getAnswerText());
                    // Exclude correct value here
                   // answerDto.setCorrect(null);
                    answerDtos.add(answerDto);
                }
                questionDto.setAnswers(answerDtos);

                questionSet.add(questionDto);
            }
        }

        return questionSet;
    }

}
