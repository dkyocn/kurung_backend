package com.kurung.exercise.repository;

import static com.kurung.exercise.entity.QExerciseLogEntity.exerciseLogEntity;

import com.kurung.exercise.dto.MonthlyExerciseDTO;
import com.kurung.exercise.dto.SummaryDTO;
import com.kurung.exercise.entity.ExerciseLogEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


@Repository
@Primary
@RequiredArgsConstructor
public class ExerciseLogRepositorySupportImpl implements ExerciseLogRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<SummaryDTO.ExerciseLogDTO> getLogsByConditionAndDate(String uuid, String condition, Date from, Date to) {
        return null;
//        return jpaQueryFactory
//                .selectFrom(log)
//                .where(
//                        Expressions.stringPath(log, "userUuid").eq(uuid),
//                        condition != null ? Expressions.stringPath(log, "condition").eq(condition) : null,
//                        Expressions.datePath(Date.class, log, "createdAt").between(from, to)
//                )
//                .fetch()
//                .stream()
//                .map(entity -> ExerciseDTO.toExerciseLogBuilder().entity((ExerciseLogEntity) entity).build())
//                .collect(Collectors.toList());
    }

    // Summary ---------------------------------------
    @Override
    public List<ExerciseLogEntity> getLogsByUserUuid(String uuid) {
            return jpaQueryFactory
            .selectFrom(exerciseLogEntity)
            .where(exerciseLogEntity.user.userUuid.eq(uuid))
            .fetch();
    }

    // ExerciseMonthlyTime ----------------------------
    @Override
    public List<ExerciseLogEntity> getMonthlyExerciseTime(String uuid, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return jpaQueryFactory
            .selectFrom(exerciseLogEntity)
            .where(
                exerciseLogEntity.user.userUuid.eq(uuid),
                exerciseLogEntity.createdAt.between(startDateTime, endDateTime)
            )
            .fetch();
    }




}
