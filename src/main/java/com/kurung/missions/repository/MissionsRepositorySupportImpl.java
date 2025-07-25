package com.kurung.missions.repository;

import com.kurung.common.enumeration.HealthType;
import com.kurung.diet.entity.DietScoreEntity;
import com.kurung.missions.entity.MissionsEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kurung.diet.entity.QDietScoreEntity.dietScoreEntity;
import static com.kurung.missions.entity.QMissionsEntity.missionsEntity;

@Repository
@RequiredArgsConstructor
public class MissionsRepositorySupportImpl implements MissionsRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<MissionsEntity> getMissionsById() {
    return jpaQueryFactory
        .selectFrom(missionsEntity)
        .fetch();
  }

  @Override
  public List<MissionsEntity> getMissionList(String userUuid, LocalDate startedDate) {

    return jpaQueryFactory
        .selectFrom(missionsEntity)
        .where(
            missionsEntity.user.userUuid.eq(userUuid),
            missionsEntity.startedDate.year().eq(startedDate.getYear()),
            missionsEntity.startedDate.month().eq(startedDate.getMonthValue()),
            missionsEntity.startedDate.dayOfMonth().eq(startedDate.getDayOfMonth())
        )
        .fetch();
  }

  @Override
  public List<MissionsEntity> getMissionMonthList(LocalDate startDate, LocalDate endDate, String userUuid, HealthType displayType) {
    return jpaQueryFactory
        .selectFrom(missionsEntity)
        .where(
            missionsEntity.user.userUuid.eq(userUuid),
            missionsEntity.startedDate.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59)),
            missionsEntity.displayType.eq(displayType)
        )
        .fetch();
  }
}
