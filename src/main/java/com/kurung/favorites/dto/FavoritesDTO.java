package com.kurung.favorites.dto;

import com.kurung.common.dto.BaseDTO;
import com.kurung.community.dto.CommunityDTO;
import com.kurung.diet.dto.FoodDTO;
import com.kurung.exercise.dto.RoutinesDTO;
import com.kurung.favorites.entity.FavoritesEntity;
import com.kurung.stressrelief.dto.StressReliefDTO;
import com.kurung.user.dto.UserDTO;
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
public class FavoritesDTO extends BaseDTO {

  @Schema(description = "즐겨찾기 ID", example = "1")
  protected int favoritesId;
  @Schema(description = "사용자 UUID", example = "user-uuid-1234")
  protected UserDTO userDTO;
  @Schema(description = "운동루틴 ID", example = "101")
  protected RoutinesDTO routinesDTO;
  @Schema(description = "음식 ID", example = "202")
  protected FoodDTO foodDTO;
  @Schema(description = "커뮤니티 게시글 ID", example = "404")
  protected CommunityDTO communityDTO;

  @Builder(builderMethodName = "toFavoritesBuilder", builderClassName = "toFavoritesBuilder")
  public FavoritesDTO(FavoritesEntity favoritesEntity) {
    this.favoritesId = favoritesEntity.getFavoritesId();
    this.userDTO = favoritesEntity.getUser() != null ? UserDTO.toUserBuilder().userEntity(favoritesEntity.getUser()).build() : null;
    this.routinesDTO = favoritesEntity.getRoutines() != null ? RoutinesDTO.toRoutinesBuilder().entity(favoritesEntity.getRoutines()).build() : null;
    this.foodDTO = favoritesEntity.getFoods() != null ? FoodDTO.toFoodBuilder().foodEntity(favoritesEntity.getFoods()).build() : null;
    this.communityDTO = favoritesEntity.getCommunity() !=null ? CommunityDTO.toCommunityBuilder().communityEntity(favoritesEntity.getCommunity()).build() : null;
  }

}
