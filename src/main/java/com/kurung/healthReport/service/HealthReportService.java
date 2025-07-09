package com.kurung.healthReport.service;

import com.kurung.healthReport.dto.HealthReportDTO;
import java.sql.Date;

public interface HealthReportService {
  HealthReportDTO getHealthReportByMonth(int reportMonth);
}
