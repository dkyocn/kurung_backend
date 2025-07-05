package com.kurung.diet.repository;

import com.kurung.diet.entity.DietEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.kurung.diet.entity.QDietEntity.dietEntity;

@Repository
@RequiredArgsConstructor
public class DietRepositorySupportImpl implements DietRepositorySupport{

    private final JPAQueryFactory jpaQueryFactory;

    public DietEntity getDietById(int id) {
        return jpaQueryFactory.selectFrom(dietEntity)
                .where(dietEntity.dietId.eq(id))
                .fetchOne();
    }
}
