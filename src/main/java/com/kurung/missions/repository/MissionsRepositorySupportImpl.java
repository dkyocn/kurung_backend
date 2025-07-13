package com.kurung.missions.repository;

import com.kurung.common.enumeration.HealthType;
import com.kurung.missions.entity.MissionsEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

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

}
