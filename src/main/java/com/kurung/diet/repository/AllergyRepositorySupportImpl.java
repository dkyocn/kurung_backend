package com.kurung.diet.repository;

import static com.kurung.diet.entity.QAllergyEntity.allergyEntity;

import com.kurung.diet.entity.AllergyEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AllergyRepositorySupportImpl implements AllergyRepositorySupport {

  private final JPAQueryFactory queryFactory;


  @Override
  public List<AllergyEntity> getAllergyList(String keyword) {
    return queryFactory.selectFrom(allergyEntity)
        .where(containKeyword(keyword))
        .fetch();
  }

  BooleanExpression containKeyword(String keyword) {
    return StringUtils.isNotBlank(keyword) ? allergyEntity.allergyName.contains(keyword) : null;
  }
}
