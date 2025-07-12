package com.kurung.missions.repository;

import com.kurung.common.enumeration.HealthType;
import com.kurung.missions.entity.MissionsEntity;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionsRepository extends JpaRepository<MissionsEntity, Integer>, MissionsRepositorySupport {

//  boolean existsByUserUserUuidAndDisplayTypeAndStartedDate(String userUuid, HealthType displayType, Date startedDate);
//
//  List<MissionsEntity> findByUserUuidAndDisplayType(String attr0, void attr1);
}
