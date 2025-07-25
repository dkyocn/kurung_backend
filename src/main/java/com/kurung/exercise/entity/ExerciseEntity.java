package com.kurung.exercise.entity;

import com.kurung.common.entity.BaseEntity;
import com.kurung.exercise.dto.ExerciseDTO;
import com.kurung.user.entity.UserEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_EXERCISE")
public class ExerciseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "EXERCISE_ID")
  private int exerciseId;

  @Column(name = "EXERCISE_NAME")
  private String exerciseName;

  @Column(name = "EXERCISE_CATEGORY")
  private String exerciseCategory;

  @Column(name = "TOOL")
  private String tool;

  @CreatedDate
  @Column(name = "CREATED_AT")
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<ExerciseLogEntity> exerciseLogs;

  @Builder(builderMethodName = "createExerciseBuilder", builderClassName = "createExerciseBuilder")
  public ExerciseEntity(ExerciseDTO exerciseDTO) {
    this.exerciseId = exerciseDTO.getExerciseId();
    this.exerciseName = exerciseDTO.getExerciseName();
    this.exerciseCategory = exerciseDTO.getExerciseCategory();
    this.tool = exerciseDTO.getTool();
    this.createdAt = exerciseDTO.getCreatedAt();
  }
}