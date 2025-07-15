package com.kurung.healthinfo.service;

import com.kurung.healthinfo.dto.HealthInfoDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface HealthInfoService {

  List<HealthInfoDTO> getHealthInfo();

  HealthInfoDTO getHealthInfoById(String userUuid, LocalDateTime targetDate);

  List<HealthInfoDTO> getHealthInfoMonthList(LocalDate currentDate, String userUuid);
}
