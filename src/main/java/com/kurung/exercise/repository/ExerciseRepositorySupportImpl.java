package com.kurung.exercise.repository;

import static com.kurung.exercise.entity.QExerciseEntity.exerciseEntity;

import com.kurung.exercise.entity.ExerciseEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExerciseRepositorySupportImpl implements ExerciseRepositorySupport {

  private final JPAQueryFactory queryFactory;

  @Override
  public ExerciseEntity getExerciseById(int id) {
    return queryFactory
        .selectFrom(exerciseEntity)
        .where(exerciseEntity.exerciseId.eq(id))
        .fetchOne();
  }

  @Override
  public List<ExerciseEntity> getAllExercises() {
    return queryFactory
        .selectFrom(exerciseEntity)
        .fetch();
  }
}
