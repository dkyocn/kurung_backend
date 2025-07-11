package com.kurung.exercise.dto;

import com.kurung.exercise.entity.ExerciseEntity;
import com.kurung.exercise.entity.ObjectiveEntity;
import com.kurung.user.dto.UserDTO;
import java.math.BigDecimal;
import java.sql.Date;
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

  private int exerciseId;
  private String exerciseName;
  private String exerciseCategory;
  private String tool;
  private LocalDateTime createdAt;

  public ExerciseDTO(ExerciseEntity entity) {
    this.exerciseId = entity.getExerciseId();
    this.exerciseName = entity.getExerciseName();
    this.exerciseCategory = entity.getExerciseCategory();
    this.tool = entity.getTool();
    this.createdAt = entity.getCreatedAt();
  }
}
