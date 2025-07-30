package com.kurung.exercise.repository;

import static com.kurung.exercise.entity.QRoutinesEntity.routinesEntity;

import com.kurung.exercise.entity.RoutinesEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoutinesRepositorySupportImpl implements RoutinesRepositorySupport {

  private final JPAQueryFactory queryFactory;

  @Override
  public RoutinesEntity getRoutinesById(int id) {
    return queryFactory
        .selectFrom(routinesEntity)
        .leftJoin(routinesEntity.user).fetchJoin()
        .where(routinesEntity.routinesId.eq(id))
        .fetchOne();
  }


  public List<RoutinesEntity> findRoutinesByUserAndDate(String userUuid, LocalDateTime start,
      LocalDateTime end) {
    return queryFactory
        .selectFrom(routinesEntity)
        .leftJoin(routinesEntity.user).fetchJoin()
        .where(
            routinesEntity.user.userUuid.eq(userUuid),
            routinesEntity.savedDate.goe(start),
            routinesEntity.savedDate.lt(end)
        )
        .fetch();
  }
}
