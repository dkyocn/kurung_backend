package com.kurung.lifeLog.entity;

import com.kurung.missions.entity.HabitRecEntity;
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

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_MONTHLY_HABIT")
public class MonthlyHabitMissionsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MONTHLY_HABIT_ID")
  private int monthlyHabitId;

  @Column(name = "MONTHLY_HABIT_DATE")
  private LocalDateTime monthlyHabitDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "HABIT_REC_ID")
  private HabitRecEntity habitRecId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_UUID", nullable = false)
  private UserEntity user;

}
