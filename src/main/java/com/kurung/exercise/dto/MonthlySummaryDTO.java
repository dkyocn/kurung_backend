package com.kurung.exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlySummaryDTO {
  private int totalDuration;
  private int totalKcal;
  private int totalRoutineCount;
  private int goalAchievementRate;
  protected int[] weeklyRoutineCounts;
  protected int[] weeklyDurations;   // ← 추가!
  protected int[] weeklyKcals;
}