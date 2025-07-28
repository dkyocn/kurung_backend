package com.kurung.lifeLog.repository;

import com.kurung.lifeLog.entity.MonthlyHabitMissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyHabitMissionsRepository extends
    JpaRepository<MonthlyHabitMissionsEntity, Integer>, MonthlyHabitMissionsRepositorySupport{

}
