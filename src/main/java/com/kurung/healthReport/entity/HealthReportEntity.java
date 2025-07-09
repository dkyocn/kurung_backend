package com.kurung.healthReport.entity;

import com.kurung.common.entity.BaseEntity;
import com.kurung.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_HEALTH_REPORT")
public class HealthReportEntity extends BaseEntity {
  @Id
  @Column(name = "REPORT_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int reportId;
  @Column(name = "HEALTH_STATUS")
  private String healthStatus;
  @Column(name = "REPORT_MONTH")
  private Date reportMonth;
  @Column(name = "PROGRESS_RATE")
  private float progressRate;
  @Column(name = "MONTHLY_SCORE")
  private int monthlyScore;
  @Column(name = "PDF_PATH")
  private String reportPdfPath;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_UUID", nullable = false)
  private UserEntity user;
}
