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
  private int favoritesId;
  @Schema(description = "사용자 UUID", example = "abc123-uuid")
  private String userUuid;
  @Schema(description = "운동루틴 ID", example = "101")
  private Integer  routinesId;
  @Schema(description = "레시피 ID", example = "202")
  private Integer  recipeId;
  @Schema(description = "스트레스 해소 게시글 ID", example = "303")
  private Integer  stressReliefId;
  @Schema(description = "커뮤니티 게시글 ID", example = "404")
  private Integer  communityId;

  @Builder(builderMethodName = "toFavoritesBuilder", builderClassName = "toFavoritesBuilder")
  public FavoritesDTO(FavoritesEntity favoritesEntity) {
    this.favoritesId = favoritesEntity.getFavoritesId();
//    this.userUuid = favoritesEntity.getUserUuid();
    this.userUuid = favoritesEntity.getUser().getUserUuid();
    this.routinesId = favoritesEntity.getRoutinesId();
    this.recipeId = favoritesEntity.getRecipeId();
    this.stressReliefId = favoritesEntity.getStressReliefId();
    this.communityId = favoritesEntity.getCommunityId();
  }
}
