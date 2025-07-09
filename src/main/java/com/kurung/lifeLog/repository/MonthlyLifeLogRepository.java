package com.kurung.lifeLog.repository;

import com.kurung.lifeLog.entity.MonthlyLifeLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyLifeLogRepository extends JpaRepository<MonthlyLifeLogEntity, Integer>, MonthlyLifeLogRepositorySupport {

}
