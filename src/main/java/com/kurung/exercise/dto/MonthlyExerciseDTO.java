package com.kurung.exercise.dto;

import com.kurung.user.dto.UserDTO;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthlyExerciseDTO {

  protected UserDTO user;
  protected LocalDateTime date;
  protected int totalDuration;
}
