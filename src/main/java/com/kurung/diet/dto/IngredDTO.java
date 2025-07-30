package com.kurung.diet.dto;

import com.kurung.diet.entity.IngredEntity;
import com.kurung.diet.enumeration.INGREDCATEGORY;
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
public class IngredDTO {

  @Schema(description = "식재료 아이디", example = "1")
  protected int ingredId;
  @Schema(description = "식재료 이름", example = "1")
  protected String ingredName;
  @Schema(description = "식재료 알레르기", example = "1")
  protected String ingredAllergy;
  @Schema(description = "식재료 카테고리", example = "1")
  protected INGREDCATEGORY ingredCategory;

  @Builder(builderMethodName = "toIngredBuilder", builderClassName = "toIngredBuilder")
  public IngredDTO(IngredEntity IngredEntity) {
    this.ingredId = IngredEntity.getIngredId();
    this.ingredName = IngredEntity.getIngredName();
    this.ingredAllergy = IngredEntity.getIngredAllergy();
    this.ingredCategory = IngredEntity.getIngredCategory();
  }
}
