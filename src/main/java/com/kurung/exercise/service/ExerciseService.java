package com.kurung.exercise.service;

import com.kurung.exercise.dto.ExerciseDTO;
import com.kurung.exercise.dto.MonthlyExerciseDTO;
import com.kurung.exercise.dto.ObjectiveDTO;
import com.kurung.exercise.dto.RoutinesDTO;
import com.kurung.exercise.dto.SummaryDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ExerciseService {


    SummaryDTO.ExerciseLogDTO createExerciseLog(SummaryDTO.ExerciseLogDTO exerciseLogDTO);
    List<SummaryDTO.ExerciseLogDTO> getExerciseLogsByUser(String uuid);
    SummaryDTO.ExerciseLogDTO getExerciseLogById(int id);
    void deleteExerciseLog(int id);

    // Summary --------------------------------------
    SummaryDTO getSummaryByUser(String uuid);

    // Objective ------------------------------------
    ObjectiveDTO getObjectiveById(int id);

    // Routines -------------------------------------
    RoutinesDTO getRoutinesById(int id);

    // Exercise -------------------------------------
    ExerciseDTO getExerciseById(int id);

    // ExerciseMonthlyTime --------------------------
    List<MonthlyExerciseDTO> getMonthlyExerciseTime(LocalDateTime timeMonth, String userUuid);

}
