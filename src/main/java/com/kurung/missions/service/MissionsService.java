package com.kurung.missions.service;

import com.kurung.missions.dto.MissionsDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface MissionsService {
  List<MissionsDTO> getMissionsList();

  List<MissionsDTO> createAndGetTodayMissions(String userUuid);

  ResponseEntity<MissionsDTO> updateMission(MissionsDTO dto);

  void deleteMission(int missionId);

//  List<MissionsDTO> getMissionsByUserAndType(String userUuid, String displayType);
}
