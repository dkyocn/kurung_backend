package com.kurung.diet.entity;

import com.kurung.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_DIET_SCORE")
public class DietScoreEntity {

  @Id
  @Column(name = "SCORE_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int scoreId;
  @Column(name = "DIET_DATE", nullable = false)
  private LocalDateTime date;
  @Column(name = "DIET_SCORE")
  private float dietScore;
  @Column(name = "DIET_SUMMARY")
  private String summary;
  @JoinColumn(name = "USER_UUID")
  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity user;
}
