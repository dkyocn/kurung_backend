package com.kurung.exercise.repository;

import static com.kurung.exercise.entity.QExerciseLogEntity.exerciseLogEntity;

import com.kurung.exercise.dto.SummaryDTO;
import com.kurung.exercise.entity.ExerciseLogEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class ExerciseLogRepositorySupportImpl implements ExerciseLogRepositorySupport {

  private final JPAQueryFactory queryFactory;

  // Exercise --------------------------------------
  @Override
  public ExerciseLogEntity getExerciseLogById(int id) {
    return queryFactory
        .selectFrom(exerciseLogEntity)
        .where(exerciseLogEntity.exerciseLogsId.eq(id))
        .fetchOne();
  }


  // Summary ---------------------------------------
  @Override
  public List<ExerciseLogEntity> getLogsByUserUuid(String userUuid) {
    return queryFactory
        .selectFrom(exerciseLogEntity)
        .where(exerciseLogEntity.user.userUuid.eq(userUuid))
        .fetch();
  }

  // ExerciseMonthlyTime ----------------------------
  @Override
  public List<ExerciseLogEntity> getMonthlyExerciseTime(String userUuid,
      LocalDateTime startDateTime, LocalDateTime endDateTime) {
    return queryFactory
        .selectFrom(exerciseLogEntity)
        .where(
            exerciseLogEntity.user.userUuid.eq(userUuid),
            exerciseLogEntity.createdAt.between(startDateTime, endDateTime)
        )
        .fetch();
  }

  @Override
  public List<ExerciseLogEntity> findDailyLogsByUserUuid(String userUuid, LocalDate date) {
    LocalDateTime start = date.atStartOfDay();
    LocalDateTime end = date.atTime(23, 59, 59);

    // 만약 QueryDSL이 아니라면 JPAQueryFactory + Expressions로 작성!
    return queryFactory
        .selectFrom(exerciseLogEntity)
        .where(
            exerciseLogEntity.user.userUuid.eq(userUuid),
            exerciseLogEntity.createdAt.between(start, end)
        )
        .fetch();
  }
}

