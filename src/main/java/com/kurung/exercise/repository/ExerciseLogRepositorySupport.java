package com.kurung.exercise.repository;

import com.kurung.exercise.entity.ExerciseLogEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ExerciseLogRepositorySupport {

  // Exercise --------------------------------
  ExerciseLogEntity getExerciseLogById(int id);

  // SUMMARY
  List<ExerciseLogEntity> getLogsByUserUuid(String userUuid);

  // SummaryDailyList ------------------------------------------
  List<ExerciseLogEntity> findDailyLogsByUserUuid(String userUuid, LocalDate date);

  // ExerciseMonthlyTime(건강리포트) ----------------------------------------
  List<ExerciseLogEntity> getMonthlyExerciseTime(String uuid, LocalDateTime startDateTime,
      LocalDateTime endDateTime);



}
