package com.kurung.exercise.dto;

import com.kurung.exercise.entity.ExerciseEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDTO {

  protected int exerciseId;
  protected String exerciseName;
  protected String exerciseCategory;
  protected String tool;
  protected LocalDateTime createdAt;

  public ExerciseDTO(ExerciseEntity entity) {
    this.exerciseId = entity.getExerciseId();
    this.exerciseName = entity.getExerciseName();
    this.exerciseCategory = entity.getExerciseCategory();
    this.tool = entity.getTool();
    this.createdAt = entity.getCreatedAt();
  }


}
