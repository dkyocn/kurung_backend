package com.kurung.diagnosis.repository;

import static com.kurung.diagnosis.entity.QHealthAnswerEntity.healthAnswerEntity;
import static com.kurung.diagnosis.entity.QHealthQuestionEntity.healthQuestionEntity;
import static com.kurung.diagnosis.entity.QHealthOptionEntity.healthOptionEntity;

import com.kurung.diagnosis.entity.HealthAnswerEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AnswerRepositorySupportImpl implements AnswerRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<HealthAnswerEntity> getByUserUuid(String userUuid) {
    return jpaQueryFactory.selectFrom(healthAnswerEntity)
        .join(healthAnswerEntity.option, healthOptionEntity).fetchJoin()
        .join(healthOptionEntity.question, healthQuestionEntity).fetchJoin()
        .where(healthAnswerEntity.user.userUuid.eq(userUuid))
        .fetch();
  }

}