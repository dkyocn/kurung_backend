package com.kurung.diagnosis.dto;

import com.kurung.common.dto.BaseDTO;
import com.kurung.diagnosis.entity.HealthOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OptionDTO extends BaseDTO {

  @Schema(description = "선지 ID", example = "1")
  protected int optionId;
  @Schema(description = "선지 번호", example = "1")
  protected int optionCode;
  @Schema(description = "선지 내용", example = "전혀 그렇지 않다")
  protected String optionText;
  @Schema(description = "서술형 여부", example = "0")
  protected int textOption;
  @Schema(description = "선지 점수", example = "1")
  protected int rawScore;


  @Builder(builderMethodName = "toOptionBuilder", builderClassName = "toOptionBuilder")
  public OptionDTO(HealthOptionEntity healthOptionEntity) {
    this.optionId = healthOptionEntity.getOptionId();
    this.optionCode = healthOptionEntity.getOptionCode();
    this.optionText = healthOptionEntity.getOptionText();
    this.textOption = healthOptionEntity.getTextOption();
    this.rawScore = healthOptionEntity.getRawScore();
  }
}
