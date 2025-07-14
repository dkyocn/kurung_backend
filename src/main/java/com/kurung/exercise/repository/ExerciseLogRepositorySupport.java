package com.kurung.exercise.repository;

import com.kurung.exercise.dto.MonthlyExerciseDTO;
import com.kurung.exercise.dto.SummaryDTO;
import com.kurung.exercise.entity.ExerciseLogEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ExerciseLogRepositorySupport {

  // Exercise --------------------------------
  ExerciseLogEntity getExerciseLogById(int id);

  // SUMMARY
  List<ExerciseLogEntity> getLogsByUserUuid(String userUuid);

  // ExerciseMonthlyTime ----------------------------------------
  List<ExerciseLogEntity> getMonthlyExerciseTime(String uuid, LocalDateTime startDateTime,
      LocalDateTime endDateTime);


}
