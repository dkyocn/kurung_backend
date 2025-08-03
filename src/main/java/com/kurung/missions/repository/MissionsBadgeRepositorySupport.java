package com.kurung.missions.repository;

import com.kurung.missions.entity.MissionsBadgeEntity;
import java.time.LocalDate;
import java.util.List;

public interface MissionsBadgeRepositorySupport {

  MissionsBadgeEntity todayMissionBadge(String userUuid, LocalDate today);

}
