package com.kurung.diet.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity(name = "TB_FOOD")
public class FoodEntity {

    @Id
    @Column(name = "FOOD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodId;
    @Column(name = "FOOD_NAME")
    private String foodName;
    @Column(name = "FOOD_PHOTO")
    private String foodPhoto;
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DietFoodEntity> dietFood;
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<FoodIngredEntity>  foodIngred;
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<FoodAllergyEntity>  foodAllergy;
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RecipeEntity> recipe;
    @OneToOne(mappedBy = "food", cascade = CascadeType.ALL)
    private NutritionalEntity nutritional;
}
