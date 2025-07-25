package com.kurung.missions.repository;

import com.kurung.common.enumeration.HealthType;
import com.kurung.missions.entity.MissionsEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MissionsRepositorySupport {

  List<MissionsEntity> getMissionsById();

  List<MissionsEntity> getMissionList(String userUuid, LocalDate startedDate);

  List<MissionsEntity> getMissionMonthList(LocalDate startDate, LocalDate endDate, String userUuid, HealthType displayType);

}
