package com.kurung.diet.repository;

import static com.kurung.diet.entity.QDietEntity.dietEntity;

import com.kurung.diet.entity.DietEntity;
import com.kurung.diet.enumeration.MEAL;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
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
    public DietEntity getCurrentDiet(LocalDateTime startTime, LocalDateTime endTime, String userUuid, MEAL meal) {
        return jpaQueryFactory.selectFrom(dietEntity)
            .where(dietEntity.dietDate.between(startTime,endTime).and(dietEntity.user.userUuid.eq(userUuid)).and(dietEntity.meal.eq(meal)))
            .fetchOne();
    }

    @Override
    public List<DietEntity> getTodayDiet(LocalDateTime startTime, LocalDateTime endTime, String userUuid) {
        return jpaQueryFactory.selectFrom(dietEntity)
            .where(dietEntity.dietDate.between(startTime,endTime).and(dietEntity.user.userUuid.eq(userUuid)))
            .fetch();
    }
}
