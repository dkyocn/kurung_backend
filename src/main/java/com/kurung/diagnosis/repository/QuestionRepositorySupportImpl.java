package com.kurung.diagnosis.repository;

import static com.kurung.diagnosis.entity.QHealthQuestionEntity.healthQuestionEntity;
import static com.kurung.diagnosis.entity.QHealthOptionEntity.healthOptionEntity;

import com.kurung.diagnosis.entity.HealthQuestionEntity;
import com.kurung.diagnosis.enumeration.Category;
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
        .orderBy(healthQuestionEntity.questionId.asc(), healthOptionEntity.optionId.asc())
        .distinct()
        .fetch();
  }

  @Override
  public List<Category> getCategory(int questionId){
   return jpaQueryFactory
       .select(healthQuestionEntity.category)
       .from(healthQuestionEntity)
       .where(healthQuestionEntity.questionId.eq(questionId))
       .fetch();
  }

  @Override
  public HealthQuestionEntity getQuestionById(int questionId){
    return jpaQueryFactory
        .selectFrom(healthQuestionEntity)
        .where(healthQuestionEntity.questionId.eq(questionId))
        .fetchOne();
  };
}