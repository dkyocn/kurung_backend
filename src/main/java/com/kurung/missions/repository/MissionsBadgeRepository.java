package com.kurung.missions.repository;

import com.kurung.missions.entity.MissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionsBadgeRepository  extends JpaRepository<MissionsEntity, Integer>, MissionsBadgeRepositorySupport{

}
