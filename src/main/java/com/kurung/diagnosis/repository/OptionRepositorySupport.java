package com.kurung.diagnosis.repository;

import com.kurung.diagnosis.entity.HealthOptionEntity;
import java.util.List;

public interface OptionRepositorySupport {

  List<HealthOptionEntity> getByOptionId(int optionId);
  HealthOptionEntity getByOneOptionId(int optionId);
}
