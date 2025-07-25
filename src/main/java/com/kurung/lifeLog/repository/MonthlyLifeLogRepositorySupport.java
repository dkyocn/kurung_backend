package com.kurung.lifeLog.repository;

import com.kurung.lifeLog.entity.MonthlyLifeLogEntity;

public interface MonthlyLifeLogRepositorySupport {
  MonthlyLifeLogEntity findByMonthlyLifeLog_UserUuidAndMonth(String userUuid, String date);
}
