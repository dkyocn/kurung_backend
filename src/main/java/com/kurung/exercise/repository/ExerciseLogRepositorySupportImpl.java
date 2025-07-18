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

  // SummaryDaily(월간, 일일, 건강리포트) -----------------------------------------
  @Override
  public List<ExerciseLogEntity> findSummarysByUserUuid(String userUuid, LocalDateTime start, LocalDateTime end) {
    return queryFactory
        .selectFrom(exerciseLogEntity)
        .where(
            exerciseLogEntity.user.userUuid.eq(userUuid),
            exerciseLogEntity.createdAt.between(start, end)
        )
        .fetch();
  }


}

