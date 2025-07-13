package com.kurung.diet.entity;

import com.kurung.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<RecipeEntity> recipe;
    @OneToOne(mappedBy = "food", cascade = CascadeType.ALL)
    private NutritionalEntity nutritional;
}
