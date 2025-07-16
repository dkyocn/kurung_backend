package com.kurung.favorites.entity;

import com.kurung.community.entity.CommunityEntity;
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
  private RoutinesEntity routinesId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "RECIPE_ID")
  private RecipeEntity recipe;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "STRESS_RELIEF_ID")
  private StressReliefEntity stressRelief;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COMMUNITY_ID")
  private CommunityEntity community;


  @Builder(builderMethodName = "createHealthInfoBuilder", builderClassName = "createHealthInfoBuilder")
  public FavoritesEntity (FavoritesDTO favoritesDTO, UserDTO userDTO) {

    this.user = UserEntity.createUserBuilder().userDTO(userDTO).build();

    this.routinesId = favoritesDTO.getRoutinesId() != null
        ? RoutinesEntity.builder().routinesId(favoritesDTO.getRoutinesId()).build()
        : null;

    this.recipe = favoritesDTO.getRecipeId() != null
        ? RecipeEntity.builder().recipeId(favoritesDTO.getRecipeId()).build()
        : null;

    this.stressRelief = favoritesDTO.getStressReliefId() != null
        ? StressReliefEntity.builder().stressReliefId(favoritesDTO.getStressReliefId()).build()
        : null;

    this.community = favoritesDTO.getCommunityId() != null
        ? CommunityEntity.builder().communityId(favoritesDTO.getCommunityId()).build()
        : null;


  }


}
