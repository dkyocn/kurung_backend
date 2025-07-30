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
  protected boolean isActive;

  @Builder(builderMethodName = "toObjectiveBuilder", builderClassName = "toObjectiveBuilder")
  public ObjectiveDTO(ObjectiveEntity objectiveEntity, UserDTO userDTO) {
    this.objectiveId = objectiveEntity.getObjectiveId();
    this.user = userDTO;
    this.objectiveTitle = objectiveEntity.getObjectiveTitle();
    this.objectiveCount = objectiveEntity.getObjectiveCount();
    this.objectiveDuration = objectiveEntity.getObjectiveDuration();
    this.objectiveWeight = objectiveEntity.getObjectiveWeight();
    this.startDate = objectiveEntity.getStartDate();
    this.endDate = objectiveEntity.getEndDate();
    this.memo = objectiveEntity.getMemo();
    this.createdAt = objectiveEntity.getCreatedAt();
    this.updatedAt = objectiveEntity.getUpdatedAt();
    this.isActive = objectiveEntity.isActive();
  }


}
