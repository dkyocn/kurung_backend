package com.kurung.exercise.repository;

import static com.kurung.exercise.entity.QObjectiveEntity.objectiveEntity;

import com.kurung.exercise.entity.ObjectiveEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class ObjectiveRepositorySupportImpl implements ObjectiveRepositorySupport {

  private final JPAQueryFactory queryFactory;

  @Override
  public ObjectiveEntity getObjectiveByMonth(LocalDateTime startDate, LocalDateTime endDate,
      String userUuid) {
    return queryFactory
        .selectFrom(objectiveEntity)
        .where(
            objectiveEntity.startDate.loe(endDate),
            objectiveEntity.endDate.goe(startDate),
            objectiveEntity.user.userUuid.eq(userUuid)
        )
        .fetchOne();
  }

  @Override
  public  ObjectiveEntity findByObjectiveId(int objectiveId) {
    return queryFactory
        .selectFrom(objectiveEntity)
        .where(objectiveEntity.objectiveId.eq(objectiveId))
        .fetchOne();
  }


}
