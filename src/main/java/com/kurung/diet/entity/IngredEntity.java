package com.kurung.diet.entity;

import com.kurung.common.entity.BaseEntity;
import com.kurung.diet.enumeration.INGREDCATEGORY;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_INGRED")
public class IngredEntity {

  @Id
  @Column(name = "INGRED_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int ingredId;
  @Column(name = "INGRED_NAME", nullable = false)
  private String ingredName;
  @Column(name = "INGRED_ALLERGY")
  private String ingredAllergy;
  @Column(name = "INGRED_CATEGORY", nullable = false)
  @Enumerated(EnumType.STRING)
  private INGREDCATEGORY ingredCategory;
  @OneToMany(mappedBy = "ingred", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<FoodIngredEntity> foodIngred;
  @OneToOne(mappedBy = "ingred")
  private NutritionalEntity nutritional;
}
