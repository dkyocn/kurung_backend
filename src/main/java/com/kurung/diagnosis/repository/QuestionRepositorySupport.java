package com.kurung.diagnosis.repository;

import com.kurung.diagnosis.dto.QuestionDTO;
import com.kurung.diagnosis.entity.HealthQuestionEntity;
import com.kurung.diagnosis.enumeration.Category;
import java.util.List;

public interface QuestionRepositorySupport {
  List<HealthQuestionEntity> getAllQuestions();
  List<Category> getCategory(int questionId);
  HealthQuestionEntity getQuestionById(int questionId);
}
