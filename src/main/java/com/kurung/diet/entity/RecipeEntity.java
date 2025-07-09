package com.kurung.diet.entity;

import com.kurung.common.entity.BaseEntity;
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
@Entity(name = "TB_RECIPE")
public class RecipeEntity {

  @Id
  @Column(name = "RECIPE_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int recipeId;
  @Column(name = "RECIPE_ORDER", nullable = false)
  private int order;
  @Column(name = "RECIPE_CONTENT", nullable = false)
  private String recipeContent;
  @JoinColumn(name = "FOOD_ID")
  @ManyToOne(fetch = FetchType.LAZY)
  private FoodEntity food;
}
