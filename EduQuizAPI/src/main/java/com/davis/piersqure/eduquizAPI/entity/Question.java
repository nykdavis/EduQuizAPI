package com.davis.piersqure.eduquizAPI.entity;

import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "question_text")
	@NotBlank(message = "Question text is required")
	private String questionText;

	@Column(name = "subject")
	@NotBlank(message = "Subject is required")
	private String subject;

	// New field for chapter
	@Column(name = "chapter")
	@NotBlank(message = "Chapter is required")
	private String chapter; 
	
	@Column(name = "level")
	@NotBlank(message = "Level is required")
	@NotNull
	private String level;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Answer> answers;

}
