package com.kurung.missions.service;

import com.kurung.missions.dto.MissionsDTO;
import java.util.ArrayList;
import java.util.List;

public interface MissionsService {
  List<MissionsDTO> getMissionsList();

  List<MissionsDTO> createAndGetTodayMissions(String userUuid);

}
