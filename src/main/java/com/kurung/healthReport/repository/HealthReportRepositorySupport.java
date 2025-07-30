package com.kurung.healthReport.repository;

import com.kurung.healthReport.entity.HealthReportEntity;
import java.time.LocalDateTime;

public interface HealthReportRepositorySupport {

  HealthReportEntity findByReportMonth(LocalDateTime reportMonth, String userUuid);
}
