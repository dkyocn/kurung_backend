package com.kurung.diet.repository;

import static com.kurung.diet.entity.QDietScoreEntity.dietScoreEntity;

import com.kurung.diet.entity.DietScoreEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DietScoreRepositorySupportImpl implements DietScoreRepositorySupport {

  private final JPAQueryFactory queryFactory;


  @Override
  public DietScoreEntity getDietScoreById(int scoreId) {
    return queryFactory.selectFrom(dietScoreEntity)
        .where(dietScoreEntity.scoreId.eq(scoreId))
        .fetchOne();
  }
}
