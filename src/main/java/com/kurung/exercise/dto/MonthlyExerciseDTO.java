package com.kurung.exercise.dto;

import com.kurung.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthlyExerciseDTO {

  private UserDTO userUuid;              // 사용자 UUID (필요시 추가)

  private String date;                  // 조회 연월 (ex: "2025-06") → 문자열로 처리

  private int totalDuration;           // 월 전체 운동 시간 (분 단위)
}
