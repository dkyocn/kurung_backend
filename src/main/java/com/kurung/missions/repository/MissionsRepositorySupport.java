package com.kurung.missions.repository;

import com.kurung.common.enumeration.HealthType;
import com.kurung.missions.entity.MissionsEntity;

import java.sql.Date;
import java.util.List;

public interface MissionsRepositorySupport {

  List<MissionsEntity> getMissionsById();

  boolean existsMission(String userUuid, HealthType displayType, Date startedDate);

  MissionsEntity findMission(String userUuid, HealthType displayType, Date startedDate);
}
