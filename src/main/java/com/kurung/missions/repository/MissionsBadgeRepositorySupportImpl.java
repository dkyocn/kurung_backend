package com.kurung.missions.repository;

import static com.kurung.missions.entity.QMissionsBadgeEntity.missionsBadgeEntity;
import static com.kurung.missions.entity.QMissionsEntity.missionsEntity;
import com.kurung.common.enumeration.HealthType;
import com.kurung.missions.entity.MissionsBadgeEntity;
import com.kurung.missions.entity.MissionsEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MissionsBadgeRepositorySupportImpl implements MissionsBadgeRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public MissionsBadgeEntity todayMissionBadge(String userUuid, LocalDate today) {
    return jpaQueryFactory.selectFrom(missionsBadgeEntity)
        .where(missionsBadgeEntity.user.userUuid.eq(userUuid), missionsBadgeEntity.missionDate.year().eq(today.getYear())
        , missionsBadgeEntity.missionDate.month().eq(today.getMonthValue())
            ,missionsBadgeEntity.missionDate.dayOfMonth().eq(today.getDayOfMonth())).fetchOne();
  }
}

