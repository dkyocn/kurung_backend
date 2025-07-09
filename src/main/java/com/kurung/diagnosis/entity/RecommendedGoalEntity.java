package com.kurung.diagnosis.entity;

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
@Entity(name = "TB_RECOMMENDED_GOAL")
public class RecommendedGoalEntity {

  @Id
  @Column(name = "GOAL_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int goalId;
  @JoinColumn(name = "HEALTH_ID")
  @ManyToOne(fetch = FetchType.LAZY)
  private HealthDiagnosisEntity healthDiagnosis;
  @Column(name = "GOAL_TITLE")
  private String goalTitle;
  @Column(name = "GOAL_TEXT")
  private String goalText;
}
