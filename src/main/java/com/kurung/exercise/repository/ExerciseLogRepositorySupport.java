package com.kurung.exercise.repository;

import com.kurung.exercise.entity.ExerciseLogEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface ExerciseLogRepositorySupport {

  // Exercise --------------------------------
  ExerciseLogEntity getExerciseLogById(int id);

  // SUMMARY
  List<ExerciseLogEntity> getLogsByUserUuid(String userUuid);

  // SummaryDailyList ------------------------------------------
  List<ExerciseLogEntity> findSummarysByUserUuid(String userUuid, LocalDateTime start, LocalDateTime date);

}
