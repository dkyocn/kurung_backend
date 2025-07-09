package com.kurung.diet.dto;

import com.kurung.common.dto.BaseDTO;
import com.kurung.diet.entity.DietEntity;
import com.kurung.diet.enumeration.MEAL;
import com.kurung.user.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.util.CollectionUtils;

import java.sql.Date;
import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DietDTO {

    @Schema(description = "식단 아이디", example = "1")
    protected int dietId;
    @Schema(description = "식단 시간", example = "BREAKFAST")
    protected MEAL meal;
    @Schema(description = "식단 날짜", example = "2025-06-14")
    protected Date dietDate;
    @Schema(description = "음식 리스트")
    protected List<FoodDTO> foodList;
    @Schema(description = "사용자 정보")
    protected UserDTO user;
    @Schema(description = "영양 성분")
    protected NutritionDTO nutrition;


    @Builder(builderMethodName = "toDietBuilder", builderClassName = "toDietBuilder")
    public DietDTO(DietEntity dietEntity) {
        this.dietId = dietEntity.getDietId();
        this.meal = dietEntity.getMeal();
        this.dietDate = dietEntity.getDietDate();
        this.foodList = !CollectionUtils.isEmpty(dietEntity.getDietFood()) ?
                dietEntity.getDietFood().stream()
                        .map(dietFoodEntity -> FoodDTO.toFoodBuilder().foodEntity(dietFoodEntity.getFood()).build()).toList() : null;
        this.user = UserDTO.toUserBuilder()
                .userEntity(dietEntity.getUser())
                .build();
        this.nutrition = dietEntity.getNutritional() != null ? NutritionDTO.toNutritionalBuilder().nutritionalEntity(dietEntity.getNutritional()).build() :  null;
    }
}
