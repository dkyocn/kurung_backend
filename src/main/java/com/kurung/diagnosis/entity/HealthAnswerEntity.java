package com.kurung.diagnosis.entity;

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
@Entity(name = "TB_HEALTH_ANSWER")
public class HealthAnswerEntity {

  @Id
  @Column(name = "ANSWER_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int answerId;
  @JoinColumn(name = "USER_UUID")
  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity user;
  @JoinColumn(name = "OPTION_ID")
  @ManyToOne(fetch = FetchType.LAZY)
  private HealthOptionEntity option;
  @Column(name="TEXT_ANSWER")
  private String textAnswer;
}
