package com.kurung.diet.entity;

import com.kurung.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_NUTRITIONAL")
public class NutritionalEntity {

  @Id
  @Column(name = "NUTRITIONAL_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int nutritionalId;
  @Column(name = "SODIUM")
  private int sodium;
  @Column(name = "CARB")
  private int carb;
  @Column(name = "SUGAR")
  private int sugar;
  @Column(name = "TRANS_FAT")
  private int transFat;
  @Column(name = "SATURATED_FAT")
  private int saturatedFat;
  @Column(name = "CHOLESTEROL")
  private int cholesterol;
  @Column(name = "PROTEIN")
  private int protein;
  @Column(name = "KCAL")
  private int kcal;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DIET_ID")
  private DietEntity diet;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FOOD_ID")
  private FoodEntity food;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "INGRED_ID")
  private IngredEntity ingred;
}
