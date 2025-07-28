package com.kurung.healthReport.service;

import com.kurung.healthReport.dto.HealthReportDTO;
import java.sql.Date;
import java.time.LocalDateTime;

public interface HealthReportService {
  HealthReportDTO getHealthReportByMonth(LocalDateTime reportMonth);
}
