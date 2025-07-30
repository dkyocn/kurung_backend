package com.kurung.missions.dto;

import com.kurung.missions.entity.HabitRecEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class HabitRecDTO {

  protected int habitId;
  protected String habitName;

  @Builder(builderMethodName = "toHabitRecBuilder", builderClassName = "toHabitRecBuilder")
  public HabitRecDTO(HabitRecEntity habitRecEntity) {
    this.habitId = habitRecEntity.getHabitRecId();
    this.habitName = habitRecEntity.getHabitTitle();
  }
}
