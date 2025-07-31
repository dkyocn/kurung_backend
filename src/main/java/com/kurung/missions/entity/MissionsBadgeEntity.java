package com.kurung.missions.entity;

import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_MISSIONS_BADGE")
public class MissionsBadgeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "BADGE_ID", nullable = false)
  private int badgeId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_UUID", nullable = false)
  private UserEntity user;

  @Column(name = "MISSION_DATE")
  private LocalDate missionDate;

  @Column(name = "MISSION_COMPLETE")
  private boolean missionComplete;



  public void updateBadge() {
    this.missionComplete = true;
  }

}
