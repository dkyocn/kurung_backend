package com.kurung.exercise.repository;

import com.kurung.exercise.entity.ExerciseEntity;
import java.util.List;

public interface ExerciseRepositorySupport {
  ExerciseEntity getExerciseById(int id);

  List<ExerciseEntity> getAllExercises();

}
