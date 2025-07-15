package com.kurung.healthinfo.repository;

import com.kurung.healthinfo.entity.HealthInfoEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface HealthInfoRepositorySupport {

  List<HealthInfoEntity> getHealthInfo();

  HealthInfoEntity findByUserAndDateBetween(String userUuid, LocalDateTime startOfDay, LocalDateTime endOfDay);

  List<HealthInfoEntity> getHealthInfoMonthList(LocalDateTime startDateTime, LocalDateTime endDateTime, String userUuid);

  HealthInfoEntity getHealthInfoById(int healthinfoId);

}
