package com.kurung.exercise.repository;


import com.kurung.exercise.entity.ObjectiveEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;



@Repository
@RequiredArgsConstructor
public class ObjectiveRepositorySupportImpl implements ObjectiveRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public ObjectiveEntity getObjectiveById(int id) {
        return null;
//        return jpaQueryFactory
//                .selectFrom(objectiveEntity)
//                .where(objectiveEntity.objectiveId.eq(id))
//                .fetchOne();
    }

}
