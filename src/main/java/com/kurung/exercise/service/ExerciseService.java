package com.kurung.exercise.service;

import com.kurung.exercise.dto.ExerciseDTO;
import com.kurung.exercise.dto.MonthlyExerciseDTO;
import com.kurung.exercise.dto.ObjectiveDTO;
import com.kurung.exercise.dto.RoutinesDTO;
import com.kurung.exercise.dto.SummaryDTO;

import com.kurung.exercise.dto.SummaryDTO.ExerciseLogDTO;
import com.kurung.exercise.entity.ObjectiveEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

public interface ExerciseService {


  void createExerciseLog(ExerciseLogDTO exerciseLogDTO);

  SummaryDTO.ExerciseLogDTO getExerciseLogById(int id);

  SummaryDTO.ExerciseLogDTO updateExerciseLog(SummaryDTO.ExerciseLogDTO exerciseLogDTO);

  void deleteExerciseLog(int id);


  // Summary --------------------------------------
  SummaryDTO getSummaryByUser(String uuid);

  // Objective ------------------------------------
  //void ObjectiveActivation(ObjectiveEntity objectiveEntity);

  ObjectiveDTO getObjectiveByMonth(LocalDateTime date, String userUuid);

  void createObjective(ObjectiveDTO objectiveDTO);

  void updateObjective(ObjectiveDTO objectiveDTO);

  void updateObjectiveaction(int objectiveId);

//    ObjectiveEntity getObjectiveById(int id);

  // Routines -------------------------------------
  RoutinesDTO getRoutinesById(int id);

  // Exercise -------------------------------------
  ExerciseDTO getExerciseById(int id);

  // ExerciseMonthlyTime --------------------------
  List<MonthlyExerciseDTO> getMonthlyExerciseTime(LocalDateTime timeMonth, String userUuid);


}
