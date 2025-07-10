package com.kurung.lifeLog.service;

import com.kurung.lifeLog.dto.LifeLogDTO;
import java.util.List;

public interface LifeLogService {

  LifeLogDTO getLifeLogById(int id);
  List<LifeLogDTO> getLifeLogList(String userUuid, String date);
}
