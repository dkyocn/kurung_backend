package com.kurung.lifeLog.repository;

import com.kurung.lifeLog.entity.LifeLogEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface LifeLogRepositorySupport {
  LifeLogEntity getLifeLogById(int id);
  List<LifeLogEntity> findByUser_UserUuidAndCreatedAtBetween(String userUuid, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
