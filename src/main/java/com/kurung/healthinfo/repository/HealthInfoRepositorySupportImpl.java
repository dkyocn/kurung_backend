package com.kurung.healthinfo.repository;

import com.kurung.healthinfo.entity.HealthInfoEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kurung.healthinfo.entity.QHealthInfoEntity.healthInfoEntity;

@Repository
@RequiredArgsConstructor
public class HealthInfoRepositorySupportImpl implements HealthInfoRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<HealthInfoEntity> getHealthInfo() {
    return jpaQueryFactory
        .selectFrom(healthInfoEntity)
        .fetch();
  }
}
