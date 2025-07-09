package com.kurung.healthReport.repository;

import com.kurung.healthReport.entity.HealthReportEntity;
import java.sql.Date;

public interface HealthReportRepositorySupport {

  HealthReportEntity findByReportMonth(Date reportMonth);
}
