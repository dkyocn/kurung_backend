package com.kurung.diet.repository;

import static com.kurung.diet.entity.QDietScoreEntity.dietScoreEntity;

import com.kurung.diet.entity.DietScoreEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
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

  @Override
  public DietScoreEntity getDietScoreByDate(LocalDateTime startDate, LocalDateTime endDate, String userUuid) {
    return queryFactory.selectFrom(dietScoreEntity)
        .where(dietScoreEntity.date.between(startDate, endDate), dietScoreEntity.user.userUuid.eq(userUuid))
        .fetchOne();
  }

  @Override
  public List<DietScoreEntity> getDietScoreMonthList(LocalDateTime startDate,
      LocalDateTime endDate, String userUuid) {
    return queryFactory.selectFrom(dietScoreEntity)
        .where(dietScoreEntity.date.between(startDate,endDate).and(dietScoreEntity.user.userUuid.eq(userUuid)))
        .fetch();
  }
}
