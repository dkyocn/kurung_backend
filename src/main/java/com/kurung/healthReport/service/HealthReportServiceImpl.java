package com.kurung.healthReport.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.common.security.service.SessionService;
import com.kurung.diet.dto.DietScoreDTO;
import com.kurung.diet.service.DietService;
import com.kurung.exercise.dto.SummaryDTO;
import com.kurung.exercise.service.ExerciseService;
import com.kurung.healthReport.dto.HealthReportDTO;
import com.kurung.healthReport.entity.HealthReportEntity;
import com.kurung.healthReport.repository.HealthReportRepository;
import com.kurung.healthinfo.dto.HealthInfoDTO;
import com.kurung.healthinfo.service.HealthInfoService;
import com.kurung.user.dto.UserDTO;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthReportServiceImpl implements HealthReportService {

  private final ExerciseService exerciseService;
  private final DietService dietService;
  private final HealthInfoService healthInfoService;
  private final HealthReportRepository healthReportRepository;
  private final SessionService sessionService;

  @Override
  public HealthReportDTO getHealthReportByMonth(LocalDateTime reportMonth) {
    UserDTO userDTO = sessionService.getUserFromToken();

    List<DietScoreDTO> dietScoreMonthList = dietService.getDietScoreMonthList(reportMonth);
    List<SummaryDTO> monthlyExerciseTime = exerciseService.getMonthlyExerciseTime(reportMonth);
    List<HealthInfoDTO> healthInfo = healthInfoService.getHealthInfoMonthList(
        reportMonth.toLocalDate());

    HealthReportEntity byReportMonth = healthReportRepository.findByReportMonth(reportMonth, userDTO.getUserUuid());
    if (byReportMonth == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.DIET_NOT_FOUND);
    }

    return HealthReportDTO.toHealthReportBuilder()
        .healthReportEntity(byReportMonth)
        .dietScore(dietScoreMonthList)
        .monthlyExercisesTimes(monthlyExerciseTime)
        .healthInfo(healthInfo)
        .build();
  }
}
