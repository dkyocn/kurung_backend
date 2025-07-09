package com.kurung.diagnosis.entity;

import com.kurung.diagnosis.enumeration.Category;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "TB_HEALTH_QUESTION")
public class HealthQuestionEntity {

  @Id
  @Column(name = "QUESTION_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int questionId;
  @Column(name = "QUESTION_CODE")
  private int questionCode;
  @Column(name = "CATEGORY")
  @Enumerated(EnumType.STRING)
  private Category category;
  @Column(name = "QUESTION_TEXT")
  private String questionText;
  @Column(name = "IS_MULTIPLE")
  private int isMultiple;
  @Column(name = "TEXT_OPTION")
  private int textOption;
  @Column(name = "HEALTH_OPTION")
  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<HealthOptionEntity> healthOption;
}
