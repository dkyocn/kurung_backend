package com.kurung.healthinfo.service;

import com.kurung.healthinfo.dto.HealthInfoDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface HealthInfoService {

  List<HealthInfoDTO> getHealthInfo();

  HealthInfoDTO getHealthInfoById(LocalDateTime currentDate);

  List<HealthInfoDTO> getHealthInfoMonthList(LocalDate currentDate);

  void updateHealthInfo(HealthInfoDTO healthInfoDTO);

  void createHealthInfo(HealthInfoDTO healthInfoDTO);
}
