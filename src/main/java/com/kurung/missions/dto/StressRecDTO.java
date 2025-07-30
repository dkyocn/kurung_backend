package com.kurung.missions.dto;

import com.kurung.missions.entity.StressRecEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StressRecDTO {

  protected int stressRecId;
  protected String stressTitle;

  @Builder(builderMethodName = "toStressRecBuilder" , builderClassName = "toStressRecBuilder")
  public StressRecDTO(StressRecEntity stressRecEntity) {
    this.stressRecId = stressRecEntity.getStressRecId();
    this.stressTitle = stressRecEntity.getStressTitle();
  }


}
