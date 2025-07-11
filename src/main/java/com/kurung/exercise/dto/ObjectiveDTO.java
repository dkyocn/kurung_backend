package com.kurung.exercise.dto;

import com.kurung.exercise.entity.ObjectiveEntity;
import com.kurung.user.dto.UserDTO;
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
public class ObjectiveDTO extends RoutinesDTO {

    private int objectiveId;
    private UserDTO user;
    private String objectiveTitle;
    private int objectiveCount;
    private int objectiveDuration;
    private BigDecimal objectiveWeight;
    private Date startDate;
    private Date endDate;
    private String memo;
    private Boolean isActive;
    private Date createdAt;
    private Date lastUpdatedAt;

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
        this.lastUpdatedAt = entity.getLastUpdatedAt();
    }
}
