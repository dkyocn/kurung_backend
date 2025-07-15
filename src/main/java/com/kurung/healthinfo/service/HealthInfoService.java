package com.kurung.healthinfo.service;

import com.kurung.healthinfo.dto.HealthInfoDTO;
import java.util.List;

public interface HealthInfoService {
  List<HealthInfoDTO> getHealthInfo();
}
