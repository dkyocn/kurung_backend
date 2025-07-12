package com.kurung.diagnosis.repository;

import static com.kurung.diagnosis.entity.QHealthQuestionEntity.healthQuestionEntity;
import static com.kurung.diagnosis.entity.QHealthOptionEntity.healthOptionEntity;

import com.kurung.diagnosis.entity.HealthQuestionEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuestionRepositorySupportImpl implements QuestionRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<HealthQuestionEntity> getAllQuestions() {
    return jpaQueryFactory
        .selectFrom(healthQuestionEntity)
        .leftJoin(healthQuestionEntity.healthOption, healthOptionEntity).fetchJoin()
        .orderBy(healthQuestionEntity.questionCode.asc(), healthOptionEntity.optionCode.asc())
        .distinct()
        .fetch();
  }
}