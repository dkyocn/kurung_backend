package com.kurung.diagnosis.repository;

import com.kurung.diagnosis.entity.HealthDiagnosisEntity;

public interface DiagnosisRepositorySupport {

  HealthDiagnosisEntity getDiagnosisByUserUuid(String userUuid);
}
