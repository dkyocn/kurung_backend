package com.kurung.exercise.repository;

import static com.kurung.exercise.entity.QRoutinesEntity.routinesEntity;

import com.kurung.exercise.entity.ObjectiveEntity;
import com.kurung.exercise.entity.RoutinesEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
}
