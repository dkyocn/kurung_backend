package com.kurung.diagnosis.entity;

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
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_HEALTH_OPTION")
public class HealthOptionEntity {

  @Id
  @Column(name = "OPTION_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int optionId;
  @Column(name = "OPTION_CODE")
  private int optionCode;
  @Column(name = "OPTION_TEXT")
  private String optionText;
  @JoinColumn(name = "QUESTION_ID")
  @ManyToOne(fetch = FetchType.LAZY)
  private HealthQuestionEntity question;
  @Column(name = "TEXT_OPTION")
  private int textOption;
  @Column(name = "HEALTH_ANSWER")
  @OneToMany(mappedBy = "option", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<HealthAnswerEntity> answers;
}
