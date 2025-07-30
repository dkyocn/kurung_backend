package com.kurung.missions.repository;

import com.kurung.missions.entity.MissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionsRepository extends JpaRepository<MissionsEntity, Integer>, MissionsRepositorySupport {
}