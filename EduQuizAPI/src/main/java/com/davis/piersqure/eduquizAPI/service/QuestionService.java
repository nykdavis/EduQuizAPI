package com.davis.piersqure.eduquizAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
