package com.davis.piersqure.eduquizAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.davis.piersqure.eduquizAPI.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
	
    @Query("SELECT DISTINCT q.chapter FROM Question q")
    List<String> findDistinctChapters();

    Question findFirstByChapter(String chapter);
    
    @Query(value = "SELECT * FROM question q WHERE q.chapter = :chapter ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Question findRandomByChapter(String chapter);
   
}
