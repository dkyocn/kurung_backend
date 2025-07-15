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
  public HealthInfoEntity findByUserAndDate(String userUuid, LocalDateTime targetDate) {
    return jpaQueryFactory
        .selectFrom(healthInfoEntity)
        .where(
            healthInfoEntity.user.userUuid.eq(userUuid),
            healthInfoEntity.updatedAt.year().eq(targetDate.getYear()),
            healthInfoEntity.updatedAt.month().eq(targetDate.getMonthValue()),
            healthInfoEntity.updatedAt.dayOfMonth().eq(targetDate.getDayOfMonth())
        )
        .orderBy(healthInfoEntity.updatedAt.desc())
        .fetchFirst();
  }
}
