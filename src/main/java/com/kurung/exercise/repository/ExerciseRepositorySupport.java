package com.kurung.exercise.repository;

import com.kurung.exercise.entity.ExerciseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExerciseRepositorySupport {
  ExerciseEntity getExerciseById(int id);

  Page<ExerciseEntity> getExercisePage(String keyword, Pageable pageable);
}
