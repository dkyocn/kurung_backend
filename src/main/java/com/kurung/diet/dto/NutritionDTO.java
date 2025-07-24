package com.kurung.diet.dto;

import com.kurung.diet.entity.NutritionalEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class NutritionDTO {

  protected int nutritionId;
  protected int sodium;
  protected int carb;
  protected int sugar;
  protected int totalFat;
  protected int transFat;
  protected int saturatedFat;
  protected int cholesterol;
  protected int protein;
  protected int kcal;

  @Builder(builderMethodName = "toNutritionalBuilder", builderClassName = "toNutritionalBuilder")
  public NutritionDTO(NutritionalEntity nutritionalEntity) {
    this.nutritionId = nutritionalEntity.getNutritionalId();
    this.sodium = nutritionalEntity.getSodium();
    this.carb = nutritionalEntity.getCarb();
    this.sugar = nutritionalEntity.getSugar();
    this.totalFat = nutritionalEntity.getTransFat() + nutritionalEntity.getSaturatedFat();
    this.transFat = nutritionalEntity.getTransFat();
    this.saturatedFat = nutritionalEntity.getSaturatedFat();
    this.cholesterol = nutritionalEntity.getCholesterol();
    this.protein = nutritionalEntity.getProtein();
    this.kcal = nutritionalEntity.getKcal();
  }
}
