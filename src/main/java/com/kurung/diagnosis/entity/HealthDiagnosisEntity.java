package com.kurung.diagnosis.entity;

import com.kurung.common.entity.BaseEntity;
import com.kurung.user.entity.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_HEALTH_DIAGNOSIS")
public class HealthDiagnosisEntity {

  @Id
  @Column(name = "HEALTH_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int healthId;
  @JoinColumn(name = "USER_UUID")
  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity user;
  @Column(name = "DIAGNOSIS_SUMMARY")
  private String diagnosisSummary;
  @Column(name = "SCORE")
  private int score;
  @Column(name = "REPORT_PDF_PATH")
  private String reportPdfPath;
  @Column(name = "CREATED_AT")
  private LocalDateTime createdAt;
  @OneToMany(mappedBy = "healthDiagnosis", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<RecommendedGoalEntity> recommendedGoal;
}
