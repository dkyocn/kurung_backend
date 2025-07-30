package com.kurung.exercise.service;

import com.kurung.exercise.dto.ExerciseDTO;
import com.kurung.exercise.dto.ObjectiveDTO;
import com.kurung.exercise.dto.RoutinesDTO;
import com.kurung.exercise.dto.SummaryDTO;
import com.kurung.exercise.dto.SummaryDTO.ExerciseLogDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

public interface ExerciseService {


  void createExerciseLog(ExerciseLogDTO exerciseLogDTO);

  ExerciseLogDTO getExerciseLogById(int id);

  ExerciseLogDTO updateExerciseLog(ExerciseLogDTO exerciseLogDTO);

  void deleteExerciseLog(int id);


  // Summary --------------------------------------
  SummaryDTO getSummaryByUser();

  // SummaryDailyList -----------------------------
  SummaryDTO getSummaryDailyList(LocalDate date);

  // SummaryMonthly --------------------------------
  SummaryDTO.MonthlySummaryDTO getMonthlySummary(YearMonth month);

  // Objective ------------------------------------

  ObjectiveDTO getObjectiveByMonth(LocalDateTime date);

  void createObjective(ObjectiveDTO objectiveDTO);

  void updateObjective(ObjectiveDTO objectiveDTO);

  void updateObjectiveaction(int objectiveId);

  ObjectiveDTO getObjectiveById(int objectiveId);

//    ObjectiveEntity getObjectiveById(int id);

  // Routines -------------------------------------
  RoutinesDTO getRoutinesById(int id);

  void createRoutine(RoutinesDTO routinesDTO);

  void deleteRoutine(int routinesId);

  List<RoutinesDTO> getRoutinesByUserAndDate(LocalDate date);

  // Exercise -------------------------------------
  ExerciseDTO getExerciseById(int id);

  List<ExerciseDTO> getAllExercises();

  // ExerciseMonthlyTime --------------------------
  List<SummaryDTO> getMonthlyExerciseTime(LocalDateTime timeMonth);


}