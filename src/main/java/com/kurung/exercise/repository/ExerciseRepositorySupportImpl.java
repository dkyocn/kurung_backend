package com.kurung.exercise.repository;

import static com.kurung.exercise.entity.QRoutinesEntity.routinesEntity;

import com.kurung.exercise.entity.RoutinesEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExerciseRepositorySupportImpl implements ExerciseRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public RoutinesEntity getRoutinesById(int id) {
    return jpaQueryFactory
        .selectFrom(routinesEntity)
        .leftJoin(routinesEntity.user).fetchJoin()
        .where(routinesEntity.routinesId.eq(id))
        .fetchOne();
  }
}
