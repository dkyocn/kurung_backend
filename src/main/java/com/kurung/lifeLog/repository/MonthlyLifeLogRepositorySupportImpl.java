package com.kurung.lifeLog.repository;

import static com.kurung.lifeLog.entity.QMonthlyLifeLogEntity.monthlyLifeLogEntity;

import com.kurung.lifeLog.entity.MonthlyLifeLogEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MonthlyLifeLogRepositorySupportImpl implements MonthlyLifeLogRepositorySupport  {

  private final JPAQueryFactory jpaQueryFactory;


  @Override
  public MonthlyLifeLogEntity findByMonthlyLifeLog_UserUuidAndMonth(String userUuid, String date) {

    return jpaQueryFactory.selectFrom(monthlyLifeLogEntity)
        .where(
            monthlyLifeLogEntity.user.userUuid.eq(userUuid),
            monthlyLifeLogEntity.month.eq(date)
        )
        .fetchOne();
  }
}
