package com.kurung.lifeLog.entity;

import com.kurung.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_Monthly_LifeLog")
public class MonthlyLifeLogEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MONTHLY_LIFELOG_ID")
  private Integer monthlyLifeLogId;

  @Column(name = "REPORT_MONTH", nullable = false)
  private String month;

  @Column(name = "MONTHLY_SUMMARY", length = 1000, nullable = false)
  private String monthlySummary;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_UUID", nullable = false)
  private UserEntity user;


}
