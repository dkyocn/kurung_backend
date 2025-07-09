package com.kurung.favorites.entity;

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
  @Column(name = "ROUTINES_ID")
  private Integer  routinesId;
  @Column(name = "RECIPE_ID" )
  private Integer  recipeId;
  @Column(name = "STRESS_RELIEF_ID")
  private Integer  stressReliefId;
  @Column(name = "COMMUNITY_ID")
  private Integer  communityId;

}
