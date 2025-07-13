package com.kurung.favorites.dto;

import com.kurung.favorites.entity.FavoritesEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoritesDTO {

  @Schema(description = "즐겨찾기 ID", example = "1")
  protected int favoritesId;
  @Schema(description = "사용자 UUID", example = "abc123-uuid")
  protected String userUuid;
  @Schema(description = "운동루틴 ID", example = "101")
  protected Integer  routinesId;
  @Schema(description = "레시피 ID", example = "202")
  protected Integer  recipeId;
  @Schema(description = "스트레스 해소 게시글 ID", example = "303")
  protected Integer  stressReliefId;
  @Schema(description = "커뮤니티 게시글 ID", example = "404")
  protected Integer  communityId;

  @Builder(builderMethodName = "toFavoritesBuilder", builderClassName = "toFavoritesBuilder")
  public FavoritesDTO(FavoritesEntity favoritesEntity) {
    this.favoritesId = favoritesEntity.getFavoritesId();
//    this.userUuid = favoritesEntity.getUserUuid();
    this.userUuid = favoritesEntity.getUser().getUserUuid();
    this.routinesId = favoritesEntity.getRoutinesId();
    this.recipeId = favoritesEntity.getRecipe() != null ? favoritesEntity.getRecipe().getRecipeId() : null;
    this.stressReliefId = favoritesEntity.getStressRelief() != null ? favoritesEntity.getStressRelief().getStressReliefId() : null;
    this.communityId = favoritesEntity.getCommunity() != null ? favoritesEntity.getCommunity().getCommunityId() : null;
  }
}
