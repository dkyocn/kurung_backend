package com.kurung.healthReport.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.healthReport.dto.HealthReportDTO;
import com.kurung.healthReport.entity.HealthReportEntity;
import com.kurung.healthReport.repository.HealthReportRepository;
import java.sql.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthReportServiceImpl implements HealthReportService {
  private final HealthReportRepository healthReportRepository;

  @Override
  public HealthReportDTO getHealthReportByMonth(int reportMonth) {

    HealthReportEntity byReportMonth = healthReportRepository.findByReportMonth(new Date(reportMonth));
    if (byReportMonth == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.DIET_NOT_FOUND);
    }

    return HealthReportDTO.toHealthReportBuilder()
        .healthReportEntity(byReportMonth)
        .build();
  }
}
