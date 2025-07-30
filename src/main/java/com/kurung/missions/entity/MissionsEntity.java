package com.kurung.missions.entity;

import com.kurung.common.enumeration.HealthType;
import com.kurung.missions.dto.MissionsDTO;
import com.kurung.user.entity.UserEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;

import java.sql.Date;

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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "HABIT_REC_ID")
  private HabitRecEntity habitRec;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "EXERCISE_REC_ID")
  private ExerciseRecEntity exerciseRec;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DIET_REC_ID")
  private DietRecEntity dietRec;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "STRESS_REC_ID")
  private StressRecEntity stressRec;
  
  public void updateMissions(MissionsDTO missionsDTO) {
    this.missionId = missionsDTO.getMissionId();
    this.startedDate = missionsDTO.getStartedDate();
    this.isComplete = missionsDTO.isComplete();
    this.displayType = missionsDTO.getDisplayType();
    this.toggleOption = missionsDTO.isToggleOption();

  }

}




