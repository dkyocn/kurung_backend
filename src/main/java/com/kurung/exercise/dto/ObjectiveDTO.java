package com.kurung.exercise.dto;

import com.kurung.common.dto.BaseDTO;
import com.kurung.exercise.entity.ObjectiveEntity;
import com.kurung.user.dto.UserDTO;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ObjectiveDTO extends BaseDTO {

    private int objectiveId;
    private UserDTO user;
    private String objectiveTitle;
    private int objectiveCount;
    private int objectiveDuration;
    private BigDecimal objectiveWeight;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String memo;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime UpdatedAt;

    public ObjectiveDTO(ObjectiveEntity entity) {
        this.objectiveId = entity.getObjectiveId();
        this.user = entity.getUser() != null
            ? UserDTO.toUserBuilder().userEntity(entity.getUser()).build()
            : null;;
        this.objectiveTitle = entity.getObjectiveTitle();
        this.objectiveCount = entity.getObjectiveCount();
        this.objectiveDuration = entity.getObjectiveDuration();
        this.objectiveWeight = entity.getObjectiveWeight();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.memo = entity.getMemo();
        this.isActive = entity.getIsActive();
        this.createdAt = entity.getCreatedAt();
        this.UpdatedAt = entity.getUpdatedAt();
    }
}
