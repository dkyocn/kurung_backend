package com.kurung.exercise.entity;

import com.kurung.common.entity.BaseEntity;
import com.kurung.exercise.dto.ObjectiveDTO;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_OBJECTIVE")
public class ObjectiveEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "OBJECTIVE_ID")
  private int objectiveId;

  @Column(name = "OBJECTIVE_TITLE")
  private String objectiveTitle;

  @Column(name = "OBJECTIVE_COUNT")
  private int objectiveCount;

  @Column(name = "OBJECTIVE_DURATION")
  private int objectiveDuration;

  @Column(name = "OBJECTIVE_WEIGHT")
  private float objectiveWeight;

  @Column(name = "START_DATE", nullable = false)
  private LocalDateTime startDate;

  @Column(name = "END_DATE", nullable = false)
  private LocalDateTime endDate;

  @Column(name = "MEMO")
  private String memo;

  @Column(name = "IS_ACTIVE", nullable = false)
  private Boolean isActive;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_UUID")
  private UserEntity user;

  @Builder(builderMethodName = "createObjectiveBuilder", builderClassName = "CreateObjectiveBuilder")
  public ObjectiveEntity(ObjectiveDTO objectiveDTO, UserDTO userDTO) {
    this.objectiveTitle = objectiveDTO.getObjectiveTitle();
    this.objectiveCount = objectiveDTO.getObjectiveCount();
    this.objectiveDuration = objectiveDTO.getObjectiveDuration();
    this.objectiveWeight = objectiveDTO.getObjectiveWeight();
    this.startDate = objectiveDTO.getStartDate();
    this.endDate = objectiveDTO.getEndDate();
    this.memo = objectiveDTO.getMemo();
    this.isActive = objectiveDTO.getIsActive();
    this.user = UserEntity.createUserBuilder().userDTO(userDTO).build();
  }

  public void updateObjective(ObjectiveDTO dto) {
    this.objectiveTitle = dto.getObjectiveTitle();
    this.objectiveCount = dto.getObjectiveCount();
    this.objectiveDuration = dto.getObjectiveDuration();
    this.objectiveWeight = dto.getObjectiveWeight();
    this.startDate = dto.getStartDate();
    this.endDate = dto.getEndDate();
    this.memo = dto.getMemo();
    this.isActive = dto.getIsActive();
  }

}
