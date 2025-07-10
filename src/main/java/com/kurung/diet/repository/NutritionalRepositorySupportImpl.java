package com.kurung.diet.repository;

import static com.kurung.diet.entity.QNutritionalEntity.nutritionalEntity;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomRunTimeException;
import com.kurung.diet.entity.NutritionalEntity;
import com.kurung.diet.enumeration.DIETTYPE;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NutritionalRepositorySupportImpl implements NutritionalRepositorySupport {

  private final JPAQueryFactory queryFactory;

  @Override
  public NutritionalEntity getNutritionalById(int id, DIETTYPE type) {
    return queryFactory.selectFrom(nutritionalEntity)
        .where(selectType(id, type))
        .fetchOne();
  }


  private BooleanExpression selectType(int id, DIETTYPE type) {
    return switch (type) {
      case DIET -> nutritionalEntity.diet.dietId.eq(id);
      case FOOD -> nutritionalEntity.food.foodId.eq(id);
      case INGRED -> nutritionalEntity.ingred.ingredId.eq(id);
    };
  }
}
