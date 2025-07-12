package com.kurung.missions.dto;

import com.kurung.common.enumeration.HealthType;
import com.kurung.missions.entity.MissionsEntity;
import io.swagger.v3.oas.annotations.media.Schema;
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
  private int missionId;

  @Schema(description = "사용자 UUID", example = "user-uuid-1234")
  private String userUuid;

  @Schema(description = "미션 시작 날짜", example = "2025-07-08")
  private Date startedDate;

  @Schema(description = "미션 완료 여부", example = "false")
  private boolean isComplete;

  @Schema(description = "미션 표시 방식 (예: badge, text)", example = "badge")
  private HealthType displayType;

  @Schema(description = "토글 옵션 사용 여부", example = "true")
  private boolean toggleOption;

  @Builder(builderMethodName = "toMissionBuilder", builderClassName = "toMissionBuilder")
  public MissionsDTO(MissionsEntity missionEntity) {
    this.missionId = missionEntity.getMissionId();
//    this.userUuid = missionEntity.getUserUuid();
    this.userUuid = missionEntity.getUser().getUserUuid();
    this.startedDate = missionEntity.getStartedDate();
    this.isComplete = missionEntity.isComplete();
    this.displayType = missionEntity.getDisplayType();
    this.toggleOption = missionEntity.isToggleOption();
  }
  public static MissionsDTO toDTO(MissionsEntity entity) {
    return MissionsDTO.builder()
        .missionId(entity.getMissionId())
        .userUuid(entity.getUser().getUserUuid())
        .startedDate(entity.getStartedDate())
        .isComplete(entity.isComplete())
        .displayType(entity.getDisplayType())
        .toggleOption(entity.isToggleOption())
        .build();
  }
}
