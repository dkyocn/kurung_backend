package com.kurung.missions.service;

import com.kurung.missions.dto.MissionsDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface MissionsService {
  List<MissionsDTO> getMissionsList();

  List<MissionsDTO> getTodayMissions(String userUuid);

  List<MissionsDTO> getMissionMonthList(LocalDateTime currentDate, String userUuid);

}