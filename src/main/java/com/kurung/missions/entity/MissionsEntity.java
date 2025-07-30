package com.kurung.missions.entity;

import com.kurung.common.enumeration.HealthType;
import com.kurung.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_MISSIONS")
public class MissionsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MISSION_ID", nullable = false)
  private int missionId;

  @Column(name = "STARTED_DATE")
  private LocalDateTime startedDate;

  @Column(name = "IS_COMPLETED", nullable = false)
  private boolean isComplete;

  @Column(name = "DISPLAY_TYPE")
  @Enumerated(EnumType.STRING)
  private HealthType displayType;

  @Column(name = "TOGGLE_OPTION", nullable = false)
  private boolean toggleOption;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_UUID", nullable = false)
  private UserEntity user;
}
