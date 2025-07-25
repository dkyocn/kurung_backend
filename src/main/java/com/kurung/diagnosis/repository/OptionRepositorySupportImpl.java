package com.kurung.diagnosis.repository;

import static com.kurung.diagnosis.entity.QHealthOptionEntity.healthOptionEntity;

import com.kurung.diagnosis.entity.HealthOptionEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OptionRepositorySupportImpl implements OptionRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<HealthOptionEntity> getByOptionId(int optionId){
    return jpaQueryFactory.selectFrom(healthOptionEntity)
        .where(healthOptionEntity.optionId.eq(optionId))
        .fetch();
  }

  @Override
  public HealthOptionEntity getByOneOptionId(int optionId){
    return jpaQueryFactory.selectFrom(healthOptionEntity)
        .where(healthOptionEntity.optionId.eq(optionId))
        .fetchOne();
  }
}
