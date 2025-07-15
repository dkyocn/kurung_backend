package com.kurung.exercise.repository;

import static com.kurung.exercise.entity.QExerciseEntity.exerciseEntity;

import com.kurung.exercise.entity.ExerciseEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
  public Page<ExerciseEntity> getExercisePage(String keyword, Pageable pageable) {

    List<ExerciseEntity> exerciseEntities = queryFactory.selectFrom(exerciseEntity)
        .where(containKeyword(keyword))
        .orderBy(exerciseEntity.exerciseId.desc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    Long count = queryFactory.select(exerciseEntity.count())
        .from(exerciseEntity)
        .where(containKeyword(keyword))
        .fetchOne();

    return new PageImpl<>(exerciseEntities, pageable, count);
  }

  BooleanExpression containKeyword(String keyword) {
    return StringUtils.isNotBlank(keyword) ? exerciseEntity.exerciseName.contains(keyword) : null;
  }
}
