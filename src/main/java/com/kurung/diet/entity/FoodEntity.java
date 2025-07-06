package com.kurung.diet.entity;

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
}
