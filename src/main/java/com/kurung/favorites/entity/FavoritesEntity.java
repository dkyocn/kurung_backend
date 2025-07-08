package com.kurung.favorites.entity;

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
@Entity(name = "TB_FAVORTIES")
public class FavoritesEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "FAVORITE_ID" , nullable = false)
  private int favoritesId;
  @Column(name = "USER_UUID" , nullable = false)
  private String userUuid;
  @Column(name = "ROUTINES_ID")
  private int routinesId;
  @Column(name = "RECIPE_ID" )
  private int recipeId;
  @Column(name = "STRESS_RELIEF_ID")
  private int stressReliefId;
  @Column(name = "COMMUNITY_ID")
  private int communityId;

}
