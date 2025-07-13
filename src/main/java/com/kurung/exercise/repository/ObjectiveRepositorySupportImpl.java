package com.kurung.exercise.repository;


import static com.kurung.exercise.entity.QObjectiveEntity.objectiveEntity;

import com.kurung.exercise.entity.ObjectiveEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;



@Repository
@RequiredArgsConstructor
public class ObjectiveRepositorySupportImpl implements ObjectiveRepositorySupport {

    private final JPAQueryFactory queryFactory;

    @Override
    public ObjectiveEntity findByObjectiveId(int id) {

        return queryFactory
            .selectFrom(objectiveEntity)
            .where(objectiveEntity.objectiveId.eq(id))
            .fetchOne();
    }

}
