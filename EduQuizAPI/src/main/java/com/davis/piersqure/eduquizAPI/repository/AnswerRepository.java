package com.davis.piersqure.eduquizAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davis.piersqure.eduquizAPI.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
	
}
