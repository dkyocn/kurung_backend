package com.kurung.missions.repository;

import com.kurung.missions.entity.MissionsEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import static com.kurung.missions.entity.QMissionsEntity.missionsEntity;

import java.util.List;

import static com.kurung.missions.entity.QMissionsEntity.missionsEntity;

@Repository
@RequiredArgsConstructor
public class MissionsRepositorySupportImpl implements MissionsRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

//  public List<MissionsEntity> getMissionsByUser(String uuid) {
//    return jpaQueryFactory
//        .selectFrom(missionsEntity)
////        .where(missionsEntity.userUuid.eq(uuid))
//        .where(missionsEntity.user.userUuid.eq(uuid))
//        .fetch();
//  }
//
//  public MissionsEntity getLatestMission(String uuid) {
//    return jpaQueryFactory
//        .selectFrom(missionsEntity)
////        .where(missionsEntity.userUuid.eq(uuid))
//        .where(missionsEntity.user.userUuid.eq(uuid))
//        .orderBy(missionsEntity.missionId.desc())
//        .limit(1)
//        .fetchOne();
//  }

  @Override
  public List<MissionsEntity> getMissionsById() {
    return jpaQueryFactory
        .selectFrom(missionsEntity)
        .fetch();
  }
}
