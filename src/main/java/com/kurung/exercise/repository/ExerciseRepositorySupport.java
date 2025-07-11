package com.kurung.exercise.repository;

import com.kurung.exercise.entity.ExerciseEntity;
import com.kurung.exercise.entity.RoutinesEntity;

public interface ExerciseRepositorySupport {
  RoutinesEntity getRoutinesById(int id);
}
