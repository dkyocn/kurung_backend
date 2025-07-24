package com.kurung.diet.repository;

import static com.kurung.diet.entity.QDietEntity.dietEntity;

import com.kurung.diet.entity.DietEntity;
import com.kurung.diet.enumeration.MEAL;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DietRepositorySupportImpl implements DietRepositorySupport{

    private final JPAQueryFactory jpaQueryFactory;

    public DietEntity getDietById(int id) {
        return jpaQueryFactory.selectFrom(dietEntity)
                .where(dietEntity.dietId.eq(id))
                .fetchOne();
    }

    @Override
    public DietEntity getCurrentDiet(LocalDateTime currentDate, String userUuid, MEAL meal) {
        return jpaQueryFactory.selectFrom(dietEntity)
            .where(dietEntity.dietDate.eq(currentDate).and(dietEntity.user.userUuid.eq(userUuid)),eqMeal(meal))
            .fetchOne();
    }

    BooleanExpression eqMeal(MEAL meal) {
        return meal != null ? dietEntity.meal.eq(meal) : null;
    }
}
