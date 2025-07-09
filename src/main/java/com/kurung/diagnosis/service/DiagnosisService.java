package com.kurung.diagnosis.service;

import com.kurung.diagnosis.dto.DiagnosisDTO;

public interface DiagnosisService {

  DiagnosisDTO getDiagnoisById(int score);
}
