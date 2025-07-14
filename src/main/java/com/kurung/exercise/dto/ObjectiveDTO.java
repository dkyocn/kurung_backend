package com.kurung.exercise.dto;

import com.kurung.common.dto.BaseDTO;
import com.kurung.exercise.entity.ObjectiveEntity;
import com.kurung.user.dto.UserDTO;
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
public class ObjectiveDTO extends BaseDTO {

  protected int objectiveId;
  protected UserDTO user;
  protected String objectiveTitle;
  protected int objectiveCount;
  protected int objectiveDuration;
  protected float objectiveWeight;
  protected LocalDateTime startDate;
  protected LocalDateTime endDate;
  protected String memo;
  protected Boolean isActive;

  @Builder(builderMethodName = "toObjectiveBuilder", builderClassName = "toObjectiveBuilder")
  public ObjectiveDTO(ObjectiveEntity entity) {
    this.objectiveId = entity.getObjectiveId();
    this.user = entity.getUser() != null
        ? UserDTO.toUserBuilder().userEntity(entity.getUser()).build()
        : null;
    this.objectiveTitle = entity.getObjectiveTitle();
    this.objectiveCount = entity.getObjectiveCount();
    this.objectiveDuration = entity.getObjectiveDuration();
    this.objectiveWeight = entity.getObjectiveWeight();
    this.startDate = entity.getStartDate();
    this.endDate = entity.getEndDate();
    this.memo = entity.getMemo();
    this.isActive = entity.getIsActive();
    this.createdAt = entity.getCreatedAt();
    this.updatedAt = entity.getUpdatedAt();
  }
}
