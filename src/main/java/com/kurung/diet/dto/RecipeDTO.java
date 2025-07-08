package com.kurung.diet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {

  @Schema(description = "레시피 아이디", example = "1")
  protected int recipeId;
  @Schema(description = "순서")
  protected int order;
  @Schema(description = "설명")
  protected String recipeContent;
}
