package com.kurung.exercise.repository;

import static com.kurung.exercise.entity.QExerciseLogEntity.exerciseLogEntity;

import com.kurung.exercise.dto.SummaryDTO;
import com.kurung.exercise.entity.ExerciseLogEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
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

    @Override
    public List<ExerciseLogEntity> getLogsByUserUuid(String userUuid) {
            return jpaQueryFactory
            .selectFrom(exerciseLogEntity)
            .where(exerciseLogEntity.user.userUuid.eq(userUuid))
            .fetch();
    }
}
