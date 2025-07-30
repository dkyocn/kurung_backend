package com.kurung.diagnosis.repository;

import com.kurung.diagnosis.entity.HealthQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<HealthQuestionEntity, Integer>, QuestionRepositorySupport {

}
