package com.kurung.diagnosis.repository;

import com.kurung.diagnosis.dto.QuestionDTO;
import com.kurung.diagnosis.entity.HealthQuestionEntity;
import java.util.List;

public interface QuestionRepositorySupport {
  List<HealthQuestionEntity> getAllQuestions();
}
