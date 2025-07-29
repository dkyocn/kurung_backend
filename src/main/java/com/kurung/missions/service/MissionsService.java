package com.kurung.missions.service;

import com.kurung.common.enumeration.HealthType;
import com.kurung.missions.dto.MissionsDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MissionsService {

  List<MissionsDTO> getMissionsList();

  List<MissionsDTO> getTodayMissions();

  List<MissionsDTO> getMissionMonthList(LocalDate currentDate, HealthType displayType);

  void updateMissions(MissionsDTO missionsDTO);
}

