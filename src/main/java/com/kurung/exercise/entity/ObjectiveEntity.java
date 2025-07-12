package com.kurung.exercise.entity;

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
public class ObjectiveEntity {

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
  private BigDecimal objectiveWeight;

  @Column(name = "START_DATE", nullable = false)
  private LocalDateTime startDate;

  @Column(name = "END_DATE", nullable = false)
  private LocalDateTime endDate;

  @Column(name = "MEMO")
  private String memo;

  @Column(name = "IS_ACTIVE", nullable = false)
  private Boolean isActive;

  @CreatedDate
  @Column(name = "CREATED_AT")
  private LocalDateTime createdAt;

  @Column(name = "UPDATED_AT")
  private LocalDateTime UpdatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_UUID")
  private UserEntity user;

}
