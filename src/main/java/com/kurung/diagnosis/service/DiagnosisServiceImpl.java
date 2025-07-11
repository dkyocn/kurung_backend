package com.kurung.diagnosis.service;

import com.kurung.common.entity.BaseEntity;
import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.diagnosis.dto.DiagnosisDTO;
import com.kurung.diagnosis.entity.HealthDiagnosisEntity;
import com.kurung.diagnosis.entity.RecommendedGoalEntity;
import com.kurung.diagnosis.repository.DiagnosisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {

  private final DiagnosisRepository diagnosisRepository;

  @Override
  public DiagnosisDTO getDiagnoisById(int healthId) {
    HealthDiagnosisEntity healthDiagnosisEntity = diagnosisRepository.getDiagnosisById(healthId);

    if (healthDiagnosisEntity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.DIET_NOT_FOUND);
    }

    return DiagnosisDTO.builder()
        .dianosisSummary(healthDiagnosisEntity.getDiagnosisSummary())
        .score(healthDiagnosisEntity.getScore())
        .reportPdfPath(healthDiagnosisEntity.getReportPdfPath())
        .createdAt(healthDiagnosisEntity.getCreatedAt())
        .build();
  }
}
