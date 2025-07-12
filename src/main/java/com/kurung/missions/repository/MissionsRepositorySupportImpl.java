package com.kurung.missions.repository;

import com.kurung.common.enumeration.HealthType;
import com.kurung.missions.entity.MissionsEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

import static com.kurung.missions.entity.QMissionsEntity.missionsEntity;

@Repository("missionsRepositorySupport")
public class MissionsRepositorySupportImpl implements MissionsRepositorySupport {

  @PersistenceContext
  private EntityManager em;

  private JPAQueryFactory jpaQueryFactory;

  // ✅ EntityManager를 기반으로 JPAQueryFactory 직접 생성
  @PostConstruct
  public void init() {
    this.jpaQueryFactory = new JPAQueryFactory(em);
  }

  @Override
  public List<MissionsEntity> getMissionsById() {
    return jpaQueryFactory
        .selectFrom(missionsEntity)
        .fetch();
  }

  @Override
  public boolean existsMission(String userUuid, HealthType displayType, Date startedDate) {
    String jpql = "SELECT COUNT(m) FROM MissionsEntity m " +
        "WHERE m.user.userUuid = :userUuid AND m.displayType = :displayType AND m.startedDate = :startedDate";

    Long count = em.createQuery(jpql, Long.class)
        .setParameter("userUuid", userUuid)
        .setParameter("displayType", displayType)
        .setParameter("startedDate", startedDate)
        .getSingleResult();

    return count > 0;
  }

  @Override
  public MissionsEntity findMission(String userUuid, HealthType displayType, Date startedDate) {
    String jpql = "SELECT m FROM MissionsEntity m " +
        "WHERE m.user.userUuid = :userUuid AND m.displayType = :displayType AND m.startedDate = :startedDate";

    return em.createQuery(jpql, MissionsEntity.class)
        .setParameter("userUuid", userUuid)
        .setParameter("displayType", displayType)
        .setParameter("startedDate", startedDate)
        .getSingleResult();
  }
}
