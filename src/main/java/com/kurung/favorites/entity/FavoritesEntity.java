package com.kurung.favorites.entity;

import com.kurung.community.entity.CommunityEntity;
import com.kurung.diet.entity.FoodEntity;
import com.kurung.diet.entity.RecipeEntity;
import com.kurung.exercise.entity.RoutinesEntity;
import com.kurung.favorites.dto.FavoritesDTO;
import com.kurung.healthinfo.dto.HealthInfoDTO;
import com.kurung.stressrelief.entity.StressReliefEntity;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_FAVORITES")
public class FavoritesEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "FAVORITE_ID" , nullable = false)
  private int favoritesId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_UUID", nullable = false)
  private UserEntity user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ROUTINES_ID")
  private RoutinesEntity routines;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FOOD_ID")
  private FoodEntity foods;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COMMUNITY_ID")
  private CommunityEntity community;


  @Builder(builderMethodName = "createHealthInfoBuilder", builderClassName = "createHealthInfoBuilder")
  public FavoritesEntity (FavoritesDTO favoritesDTO, UserDTO userDTO) {

    this.user = UserEntity.createUserBuilder().userDTO(userDTO).build();

    this.routines = favoritesDTO.getRoutinesDTO() != null
        ? RoutinesEntity.createRoutinesBuilder().routinesDTO(favoritesDTO.getRoutinesDTO()).build()
        : null;

    this.foods = favoritesDTO.getFoodDTO() != null
        ? FoodEntity.builder().foodId(favoritesDTO.getFoodDTO().getFoodId()).foodName(favoritesDTO.getFoodDTO().getFoodName()).foodPhoto(favoritesDTO.getFoodDTO().getFoodPhoto()).build()
        : null;

    this.community = favoritesDTO.getCommunityDTO() != null
        ? CommunityEntity.toCommunityBuilder().communityDTO(favoritesDTO.getCommunityDTO()).build()
        : null;
  }
}
