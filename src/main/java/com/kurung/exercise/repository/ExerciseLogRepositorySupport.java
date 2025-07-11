package com.kurung.exercise.repository;

import com.kurung.exercise.dto.MonthlyExerciseDTO;
import com.kurung.exercise.dto.SummaryDTO;
import com.kurung.exercise.entity.ExerciseLogEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ExerciseLogRepositorySupport {
  List<SummaryDTO.ExerciseLogDTO> getLogsByConditionAndDate(String uuid, String condition, Date from, Date to);


  // SUMMARY
    List<ExerciseLogEntity> getLogsByUserUuid(String uuid);

  // ExerciseMonthlyTime ----------------------------------------
  List<ExerciseLogEntity> getMonthlyExerciseTime(String uuid, LocalDateTime startDateTime, LocalDateTime endDateTime);




}
