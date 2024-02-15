package com.davis.piersqure.eduquizAPI.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private Long id;
    private String questionText;
    private List<AnswerDto> answers;
}
