package com.kurung.diet.dto;

import com.kurung.diet.entity.FoodEntity;
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
public class FoodDTO {

    @Schema(description = "음식 아이디", example = "1")
    protected int foodId;
    @Schema(description = "음식 이름", example = "김치")
    protected String foodName;
    @Schema(description = "음식 사진", example = "../images/food/kimchi")
    protected String foodPhoto;

    @Builder(builderMethodName = "toFoodBuilder", builderClassName = "toFoodBuilder")
    public FoodDTO(FoodEntity foodEntity) {
        this.foodId = foodEntity.getFoodId();
        this.foodName = foodEntity.getFoodName();
        this.foodPhoto = foodEntity.getFoodPhoto();
    }
}
