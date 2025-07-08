package com.kurung.missions.entity;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.MappedSuperclass;

import java.sql.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_MISSION")
public class MissionsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MISSION_ID", nullable = false)
  private int missionId;

  @Column(name = "USER_UUID", nullable = false)
  private String userUuid;

  @Column(name = "STARTED_DATE")
  private Date startedDate;

  @Column(name = "IS_COMPLETED", nullable = false)
  private boolean isComplete;

  @Column(name = "DISPLAY_TYPE")
  private String displayType;

  @Column(name = "TOGGLE_OPTION", nullable = false)
  private boolean toggleOption;

}
