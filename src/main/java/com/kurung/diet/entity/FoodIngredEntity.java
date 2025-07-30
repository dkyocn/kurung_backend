package com.kurung.diet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_FOOD_INGRED")
public class FoodIngredEntity {

  @Id
  @Column(name = "FOOD_INGRED_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int foodIngredId;
  @JoinColumn(name = "FOOD_ID")
  @ManyToOne(fetch = FetchType.LAZY)
  private FoodEntity food;
  @JoinColumn(name = "INGRED_ID")
  @ManyToOne(fetch = FetchType.LAZY)
  private IngredEntity ingred;
}
