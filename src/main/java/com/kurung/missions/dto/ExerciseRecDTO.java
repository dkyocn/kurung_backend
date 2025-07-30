package com.kurung.missions.dto;

import com.kurung.missions.entity.ExerciseRecEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseRecDTO {

  protected int exerciseRecId;
  protected String exerciseTitle;

  @Builder(builderMethodName = "toExerciseRecBuilder" , builderClassName ="toExerciseRecBuilder" )
  public ExerciseRecDTO(ExerciseRecEntity exerciseRecEntity) {
    this.exerciseRecId = exerciseRecEntity.getExerciseRecId();
    this.exerciseTitle = exerciseRecEntity.getExerciseTitle();
  }


}
