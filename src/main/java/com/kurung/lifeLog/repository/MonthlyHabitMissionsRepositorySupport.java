package com.kurung.lifeLog.repository;

import com.kurung.lifeLog.entity.MonthlyHabitMissionsEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface MonthlyHabitMissionsRepositorySupport {

  List<MonthlyHabitMissionsEntity> findByUser_UserUuidAndMonthlyHabitDate(String userUuid, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
