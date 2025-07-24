package com.kurung.diet.repository;

import static com.kurung.diet.entity.QFoodEntity.foodEntity;

import com.kurung.diet.entity.FoodEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FoodRepositorySupportImpl implements FoodRepositorySupport {

  private final JPAQueryFactory queryFactory;

  @Override
  public FoodEntity getFoodById(int id) {
    return queryFactory.selectFrom(foodEntity)
        .where(foodEntity.foodId.eq(id))
        .fetchOne();
  }

  @Override
  public List<FoodEntity> getFoodList(String keyword) {
    return queryFactory.selectFrom(foodEntity)
        .where(containKeyword(keyword))
        .fetch();
  }

  // keyword 유무 조건 적용
  private BooleanExpression containKeyword(String keyword) {
    return StringUtils.isNotBlank(keyword) ? foodEntity.foodName.contains(keyword) : null;
  }
}
