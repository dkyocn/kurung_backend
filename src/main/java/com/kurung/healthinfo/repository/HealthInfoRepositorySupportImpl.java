package com.kurung.healthinfo.repository;

import com.kurung.healthinfo.entity.HealthInfoEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
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


  @Override
  public HealthInfoEntity findByUserAndDateBetween(String userUuid, LocalDateTime start, LocalDateTime end) {
    return jpaQueryFactory
        .selectFrom(healthInfoEntity)
        .where(
            healthInfoEntity.user.userUuid.eq(userUuid),
            healthInfoEntity.updatedAt.between(start, end)
        )
        .fetchFirst();
  }

  @Override
  public List<HealthInfoEntity> getHealthInfoMonthList(LocalDateTime startDateTime, LocalDateTime endDateTime, String userUuid) {
    return jpaQueryFactory
        .selectFrom(healthInfoEntity)
        .where(
            healthInfoEntity.user.userUuid.eq(userUuid),
            healthInfoEntity.updatedAt.between(startDateTime, endDateTime)
        )
        .orderBy(healthInfoEntity.updatedAt.desc())
        .fetch();
  }


}
