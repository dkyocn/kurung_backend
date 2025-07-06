package com.kurung.diet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_DIET_FOOD")
public class DietFoodEntity {

    @Id
    @Column(name = "DIET_FOOD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dietFoodId;
    @JoinColumn(name = "FOOD_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private FoodEntity food;
    @JoinColumn(name = "DIET_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private DietEntity diet;
}
