package com.kurung.healthinfo.repository;

import com.kurung.healthinfo.entity.HealthInfoEntity;
import java.util.List;

public interface HealthInfoRepositorySupport {

  List<HealthInfoEntity> getHealthInfo();
}
