package com.kurung.exercise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
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
}
