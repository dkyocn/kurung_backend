package com.kurung.healthinfo.repository;

import com.kurung.healthinfo.entity.HealthInfoEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface HealthInfoRepositorySupport {

  List<HealthInfoEntity> getHealthInfo();

  HealthInfoEntity findByUserAndDate(String userUuid, LocalDateTime targetDate);

}
