package com.kurung.missions.service;

import com.kurung.missions.dto.MissionsDTO;
import java.util.List;

public interface MissionsService {
  List<MissionsDTO> getMissionsList();
}
