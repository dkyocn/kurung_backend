package com.kurung.missions.service;

import com.kurung.missions.dto.MissionsDTO;

public interface MissionsService {

  MissionsDTO getMissions(int id);

  MissionsDTO getMissionsById(int id);
}
