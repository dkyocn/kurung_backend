package com.kurung.lifeLog.repository;


import static com.kurung.lifeLog.entity.QLifeLogEntity.lifeLogEntity;

import com.kurung.lifeLog.entity.LifeLogEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
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

  @Override
  public List<LifeLogEntity> findByUser_UserUuidAndCreatedAtBetween(
      String userUuid, LocalDateTime startDateTime, LocalDateTime endDateTime) {

    return jpaQueryFactory
        .selectFrom(lifeLogEntity)
        .where(
            lifeLogEntity.user.userUuid.eq(userUuid),
            lifeLogEntity.lifelogDate.between(startDateTime, endDateTime)
        )
        .fetch();
  }


}
