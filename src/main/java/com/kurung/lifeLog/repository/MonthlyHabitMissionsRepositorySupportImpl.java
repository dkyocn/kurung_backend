package com.kurung.lifeLog.repository;

import static com.kurung.lifeLog.entity.QMonthlyHabitMissionsEntity.monthlyHabitMissionsEntity;
import static com.kurung.missions.entity.QHabitRecEntity.habitRecEntity;

import com.kurung.lifeLog.entity.MonthlyHabitMissionsEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MonthlyHabitMissionsRepositorySupportImpl implements MonthlyHabitMissionsRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<MonthlyHabitMissionsEntity> findByUser_UserUuidAndMonthlyHabitDate(String userUuid,
      LocalDateTime startDateTime, LocalDateTime endDateTime) {
    return jpaQueryFactory.selectFrom(monthlyHabitMissionsEntity)
        .where(
            monthlyHabitMissionsEntity.user.userUuid.eq(userUuid),
            monthlyHabitMissionsEntity.monthlyHabitDate.between(startDateTime, endDateTime)
            ).fetch();
  }
}
