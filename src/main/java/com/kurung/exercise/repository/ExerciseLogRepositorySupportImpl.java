package com.kurung.exercise.repository;

import static com.kurung.exercise.entity.QExerciseLogEntity.exerciseLogEntity;

import com.kurung.exercise.dto.SummaryDTO;
import com.kurung.exercise.entity.ExerciseLogEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
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

    @Override
    public List<SummaryDTO.ExerciseLogDTO> getLogsByConditionAndDate(String userUuid, String condition, Date from, Date to) {
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
    public List<ExerciseLogEntity> getLogsByUserUuid(String userUuid) {
            return queryFactory
            .selectFrom(exerciseLogEntity)
            .where(exerciseLogEntity.user.userUuid.eq(userUuid))
            .fetch();
    }

    // ExerciseMonthlyTime ----------------------------
    @Override
    public List<ExerciseLogEntity> getMonthlyExerciseTime(String userUuid, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return queryFactory
            .selectFrom(exerciseLogEntity)
            .where(
                exerciseLogEntity.user.userUuid.eq(userUuid),
                exerciseLogEntity.createdAt.between(startDateTime, endDateTime)
            )
            .fetch();
    }




}
