package com.kurung.missions.dto;

import com.kurung.missions.entity.DietRecEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DietRecDTO {

  protected int dietRecId;
  protected String dietTitle;

  @Builder(builderMethodName = "toDietRecBuilder" , builderClassName =  "toDietRecBuilder")
  public DietRecDTO(DietRecEntity dietRecEntity) {
    this.dietRecId = dietRecEntity.getDietRecId();
    this.dietTitle = dietRecEntity.getDietTitle();
  }

}
