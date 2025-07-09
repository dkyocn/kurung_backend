package com.kurung.healthReport.repository;

import com.kurung.healthReport.entity.HealthReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthReportRepository extends JpaRepository<HealthReportEntity, Integer>, HealthReportRepositorySupport{

}
