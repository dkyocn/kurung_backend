package com.kurung.diagnosis.repository;

import static com.kurung.diagnosis.entity.QRecommendedGoalEntity.recommendedGoalEntity;

import com.kurung.diagnosis.entity.RecommendedGoalEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GoalRepositorySupportImpl implements GoalRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  public List<RecommendedGoalEntity> getGoalById(int id) {
    return jpaQueryFactory.selectFrom(recommendedGoalEntity)
        .where(recommendedGoalEntity.healthDiagnosis.healthId.eq(id))
        .fetch();
  }
}
