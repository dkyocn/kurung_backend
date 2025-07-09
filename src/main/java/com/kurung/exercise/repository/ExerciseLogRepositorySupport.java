package com.kurung.exercise.repository;

import com.kurung.exercise.dto.SummaryDTO;
import com.kurung.exercise.entity.ExerciseLogEntity;

import java.sql.Date;
import java.util.List;

public interface ExerciseLogRepositorySupport {
  List<SummaryDTO.ExerciseLogDTO> getLogsByConditionAndDate(String uuid, String condition, Date from, Date to);


  // SUMMARY
    List<ExerciseLogEntity> getLogsByUserUuid(String userUuid);


}
