package com.kurung.missions.entity;

import com.kurung.common.enumeration.HealthType;
import com.kurung.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_MISSIONS")  // ✅ 테이블 이름 지정
public class MissionsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MISSION_ID", nullable = false)
  private int missionId;

  @Column(name = "STARTED_DATE")
  private Date startedDate;

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
