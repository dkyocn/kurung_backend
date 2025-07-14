package com.kurung.exercise.repository;

import static com.kurung.diet.entity.QDietScoreEntity.dietScoreEntity;
import static com.kurung.exercise.entity.QObjectiveEntity.objectiveEntity;

import com.kurung.exercise.entity.ObjectiveEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class ObjectiveRepositorySupportImpl implements ObjectiveRepositorySupport {

  private final JPAQueryFactory queryFactory;

  @Override
  public List<ObjectiveEntity> getObjectiveMonthList(LocalDateTime startDate,
      LocalDateTime endDate,
      String userUuid) {
    return queryFactory.selectFrom(objectiveEntity)
        .where(objectiveEntity.startDate.loe(endDate) // 종료일보다 늦게 시작한 건 제외
            .and(objectiveEntity.endDate.goe(startDate)) // 시작일보다 빨리 끝난 건 제외
            .and(objectiveEntity.user.userUuid.eq(userUuid)))
        .fetch();
  }




    /*@Override
    public ObjectiveEntity getObjectiveById(int id) {
        return queryFactory
            .selectFrom(objectiveEntity)
            .where(objectiveEntity.objectiveId.eq(id))
            .fetchOne();
    }*/

}
