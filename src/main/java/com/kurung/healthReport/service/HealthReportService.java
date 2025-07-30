package com.kurung.healthReport.service;

import com.kurung.healthReport.dto.HealthReportDTO;
import java.time.LocalDateTime;

public interface HealthReportService {
  HealthReportDTO getHealthReportByMonth(LocalDateTime reportMonth);
}
