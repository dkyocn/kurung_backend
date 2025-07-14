package com.kurung.diet.dto;

import com.kurung.diet.entity.FoodEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
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
    @Schema(description = "식재료 리스트")
    protected List<IngredDTO> ingredList;
    @Schema(description = "레시피 리스트")
    protected List<RecipeDTO> recipeList;
    @Schema(description = "영양 성분")
    protected NutritionDTO nutrition;

    @Builder(builderMethodName = "toFoodBuilder", builderClassName = "toFoodBuilder")
    public FoodDTO(FoodEntity foodEntity) {
        this.foodId = foodEntity.getFoodId();
        this.foodName = foodEntity.getFoodName();
        this.foodPhoto = foodEntity.getFoodPhoto();
        this.ingredList = foodEntity.getFoodIngred() != null ? foodEntity.getFoodIngred().stream().map(foodIngredEntity -> IngredDTO.toIngredBuilder().IngredEntity(foodIngredEntity.getIngred()).build()).collect(
            Collectors.toList()) : null;
        this.recipeList = foodEntity.getRecipe() != null ? foodEntity.getRecipe().stream().map(recipeEntity -> RecipeDTO.builder()
            .recipeId(recipeEntity.getRecipeId())
            .order(recipeEntity.getOrder())
            .recipeContent(recipeEntity.getRecipeContent()).build()).collect(Collectors.toList()) : null ;
    }
}
