package com.kurung.exercise.dto;


import com.kurung.common.dto.BaseDTO;
import com.kurung.exercise.entity.ExerciseLogEntity;
import com.kurung.user.dto.UserDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SummaryDTO {

  protected LocalDate date;
  protected int totalDuration;
  protected int totalKcal;
  protected int routineCount;
  protected int goalAchievementRate;
  protected List<SummaryDTO.ExerciseLogDTO> exerciseList;

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ExerciseLogDTO {

    protected int exerciseLogsId;
    protected UserDTO user;
    protected ExerciseDTO exercise;
    protected String preCondition;
    protected int duration;
    protected String intensity;
    protected int calories;
    protected int heartRate;
    protected int setCount;
    protected int repCount;
    protected String bodyCondition;
    protected String postFeeling;
    protected String physicalNote;
    protected String memo;
    protected LocalDateTime createdAt;

    @Builder(builderMethodName = "toExerciseLogBuilder", builderClassName = "toExerciseLogBuilder")
    public ExerciseLogDTO(ExerciseLogEntity entity) {
      this.exerciseLogsId = entity.getExerciseLogsId();
      this.user = entity.getUser() != null
          ? UserDTO.toUserBuilder().userEntity(entity.getUser()).build()
          : null;
      this.exercise = entity.getExercise() != null
          ? new ExerciseDTO(entity.getExercise())
          : null;
      this.preCondition = entity.getPreCondition();
      this.duration = entity.getDuration();
      this.intensity = entity.getIntensity();
      this.calories = entity.getCalories();
      this.heartRate = entity.getHeartRate();
      this.setCount = entity.getSetCount();
      this.repCount = entity.getRepCount();
      this.bodyCondition = entity.getBodyCondition();
      this.postFeeling = entity.getPostFeeling();
      this.physicalNote = entity.getPhysicalNote();
      this.memo = entity.getMemo();
      this.createdAt = entity.getCreatedAt();
    }

  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @SuperBuilder
  public static class MonthlySummaryDTO extends SummaryDTO {
    protected int[] weeklyRoutineCounts;
    protected int[] weeklyDurations;
    protected int[] weeklyKcals;
  }
}
