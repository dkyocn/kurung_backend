package com.kurung.healthinfo.service;

import com.kurung.diet.enumeration.MEAL;
import com.kurung.healthinfo.dto.HealthInfoDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface HealthInfoService {
  List<HealthInfoDTO> getHealthInfo();
}
