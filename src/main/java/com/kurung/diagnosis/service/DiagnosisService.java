package com.kurung.diagnosis.service;

import com.kurung.diagnosis.dto.DiagnosisDTO;
import com.kurung.diagnosis.dto.QuestionDTO;
import com.kurung.diagnosis.dto.AnswerDTO;
import java.util.List;

public interface DiagnosisService {
  List<QuestionDTO> getAllQuestions();
  DiagnosisDTO getDiagnosisResult();
  void createAnswers(List<AnswerDTO> answerList);
}
