package com.kurung.stressrelief.repository;

import static com.kurung.stressrelief.entity.QStressReliefEntity.stressReliefEntity;

import com.kurung.stressrelief.entity.StressReliefEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;



@Repository
@RequiredArgsConstructor
public class StressReliefRepositorySupportImpl implements StressReliefRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public StressReliefEntity getStressReliefById(int id) {
        return jpaQueryFactory
                .selectFrom(stressReliefEntity)
                .where(stressReliefEntity.stressReliefId.eq(id))
                .fetchOne();
    }
}
