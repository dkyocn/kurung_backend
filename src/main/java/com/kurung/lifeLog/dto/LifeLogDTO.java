package com.kurung.lifeLog.dto;

import com.kurung.common.dto.BaseDTO;
import com.kurung.lifeLog.entity.LifeLogEntity;
import com.kurung.user.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Date;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LifeLogDTO extends BaseDTO {

  @Schema(description = "라이프 로그 아이디", example = "1")
  protected int lifelogId;
  @Schema(description = "라이프 로그 작성 날짜")
  protected LocalDateTime lifelogDate;
  @Schema(description = "라이프 로그 감정", example = "1")
  protected String emotion;
  @Schema(description = "라이프 로그 한 줄 감정", example = "1")
  protected String emotionWrite;
  @Schema(description = "라이프 로그 잠든 시간", example = "1")
  protected LocalDateTime bedTime;
  @Schema(description = "라이프 로그 기상 시간", example = "1")
  protected LocalDateTime wakeupTime;
  @Schema(description = "라이프 로그 활동량", example = "1")
  protected String activity;
  @Schema(description = "라이프 로그 일기", example = "1")
  protected String memo;
  @Schema(description = "라이프 로그 아이디", example = "1")
  protected String llPdfPath;
  @Schema(description = "라이프 로그 아이디", example = "1")
  protected UserDTO user;

  @Builder(builderMethodName = "toLifeLogBuilder", builderClassName = "toLifeLogBuilder")
  public LifeLogDTO(LifeLogEntity lifeLogEntity) {
    this.lifelogId = lifeLogEntity.getLifelogId();
    this.lifelogDate = lifeLogEntity.getLifelogDate();
    this.emotion = lifeLogEntity.getEmotion();
    this.emotionWrite = lifeLogEntity.getEmotionWrite();
    this.bedTime = lifeLogEntity.getBedTime();
    this.wakeupTime = lifeLogEntity.getWakeupTime();
    this.activity = lifeLogEntity.getActivity();
    this.memo = lifeLogEntity.getMemo();
    this.createdAt = lifeLogEntity.getCreatedAt();
    this.updatedAt = lifeLogEntity.getUpdatedAt();
    this.llPdfPath = lifeLogEntity.getLlPdfPath();
    this.user = UserDTO.toUserBuilder()
        .userEntity(lifeLogEntity.getUser())
        .build();
  }
}
