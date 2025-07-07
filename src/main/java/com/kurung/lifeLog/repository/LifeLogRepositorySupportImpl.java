package com.kurung.lifeLog.repository;

import static com.kurung.lifeLog.entity.QLifeLogEntity.lifeLogEntity;

import com.kurung.lifeLog.entity.LifeLogEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LifeLogRepositorySupportImpl implements LifeLogRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  public LifeLogEntity getLifeLogById(int id) {
    return jpaQueryFactory.selectFrom(lifeLogEntity)
        .where(lifeLogEntity.lifelogId.eq(id)).fetchOne();
  }


}
