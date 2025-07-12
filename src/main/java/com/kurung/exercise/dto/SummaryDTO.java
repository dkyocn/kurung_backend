package com.kurung.exercise.dto;


import com.kurung.exercise.entity.ExerciseLogEntity;
import com.kurung.user.dto.UserDTO;
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

    private String date;
    private int totalDuration;
    private int totalKcal;
    private int routineCount;
    private int goalAchievementRate;

    private List<SummaryDTO.ExerciseLogDTO> exerciseList;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExerciseLogDTO {

        private int exerciseLogsId;
        private UserDTO user;
        private int exerciseId;
        private String preCondition;
        private int duration;
        private String intensity;
        private Integer calories;
        private Integer heartRate;
        private Integer setCount;
        private Integer repCount;
        private String bodyCondition;
        private String postFeeling;
        private String physicalNote;
        private String memo;
        private LocalDateTime createdAt;

        @Builder(builderMethodName = "toExerciseLogBuilder", builderClassName = "toExerciseLogBuilder")
        public ExerciseLogDTO(ExerciseLogEntity entity) {
            this.exerciseLogsId = entity.getExerciseLogsId();
            this.user = entity.getUser() != null
                ? UserDTO.toUserBuilder().userEntity(entity.getUser()).build()
                : null;
            this.exerciseId = entity.getExerciseId();
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
}
