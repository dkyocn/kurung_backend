package com.kurung.missions.dto;

import com.kurung.common.enumeration.HealthType;
import com.kurung.missions.entity.MissionsEntity;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.dto.UserDTO.toUserBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionsDTO {

  @Schema(description = "미션 ID", example = "1")
  protected int missionId;

  @Schema(description = "사용자 UUID", example = "user-uuid-1234")
  protected UserDTO userDTO;

  @Schema(description = "미션 시작 날짜", example = "2025-07-08")
  protected LocalDateTime startedDate;

  @Schema(description = "미션 완료 여부", example = "false")
  protected boolean isComplete;

  @Schema(description = "미션 표시 방식 (예: badge, text)", example = "badge")
  protected HealthType displayType;

  @Schema(description = "토글 옵션 사용 여부", example = "true")
  protected boolean toggleOption;

  @Schema(description = "습관 미션 ID" ,  example = "16")
  protected HabitRecDTO habitRecDTO;

  @Schema(description = "운동 미션 ID", example = "18")
  protected ExerciseRecDTO exerciseRecDTO;

  @Schema(description = "식단 미션  ID", example = "5")
  protected DietRecDTO dietRecDTO;

  @Schema(description = "스트레스 미션 ID", example = "4")
  protected StressRecDTO stressRecDTO;


  @Builder(builderMethodName = "toMissionBuilder", builderClassName = "toMissionBuilder")
  public MissionsDTO(MissionsEntity missionEntity) {
    this.missionId = missionEntity.getMissionId();
    this.userDTO = missionEntity.getUser() != null ? UserDTO.toUserBuilder().userEntity(missionEntity.getUser()).build() : null;
    this.startedDate = missionEntity.getStartedDate();
    this.isComplete = missionEntity.isComplete();
    this.displayType = missionEntity.getDisplayType();
    this.toggleOption = missionEntity.isToggleOption();
    this.habitRecDTO = missionEntity.getHabitRec() != null ? HabitRecDTO.toHabitRecBuilder().habitRecEntity(missionEntity.getHabitRec()).build() : null;
    this.exerciseRecDTO = missionEntity.getExerciseRec() != null ? ExerciseRecDTO.toExerciseRecBuilder().exerciseRecEntity(missionEntity.getExerciseRec()).build() : null;
    this.dietRecDTO = missionEntity.getDietRec() != null ? DietRecDTO.toDietRecBuilder().dietRecEntity(missionEntity.getDietRec()).build() : null;
    this.stressRecDTO = missionEntity.getStressRec() != null ? StressRecDTO.toStressRecBuilder().stressRecEntity(missionEntity.getStressRec()).build() : null;


  }
}
