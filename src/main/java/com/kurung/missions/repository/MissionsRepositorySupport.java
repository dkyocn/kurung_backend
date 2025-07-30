package com.kurung.missions.repository;

import com.kurung.common.enumeration.HealthType;
import com.kurung.missions.entity.MissionsEntity;
import java.time.LocalDate;
import java.util.List;

public interface MissionsRepositorySupport {

  List<MissionsEntity> getMissionsById();

  List<MissionsEntity> getMissionList(String userUuid, LocalDate startedDate);

  List<MissionsEntity> getMissionMonthList(LocalDate startDate, LocalDate endDate, String userUuid, HealthType displayType);

}
