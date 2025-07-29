package com.kurung.diet.entity;

import com.kurung.common.entity.BaseEntity;
import com.kurung.diet.dto.DietDTO;
import com.kurung.diet.dto.NutritionDTO;
import com.kurung.diet.entity.NutritionalEntity.createNutritionalBuilder;
import com.kurung.diet.enumeration.MEAL;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import org.springframework.util.CollectionUtils;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_DIET")
public class DietEntity {

  @Id
  @Column(name = "DIET_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int dietId;
  @Column(name = "MEAL", nullable = false)
  @Enumerated(EnumType.STRING)
  private MEAL meal;
  @Column(name = "DIET_DATE", nullable = false)
  private LocalDateTime dietDate;
  @JoinColumn(name = "USER_UUID", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity user;
  @Column(name = "DIET_FOOD")
  @OneToMany(mappedBy = "diet", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<DietFoodEntity> dietFood;
  @OneToOne(mappedBy = "diet", cascade = CascadeType.ALL)
  private NutritionalEntity nutritional;

  @Builder(builderMethodName = "createDietBuilder", builderClassName = "createDietBuilder")
  public DietEntity(DietDTO dietDTO, UserDTO userDTO) {
    this.dietId = dietDTO.getDietId();
    this.meal = dietDTO.getMeal();
    this.dietDate = dietDTO.getDietDate();
    this.user = UserEntity.createUserBuilder().userDTO(userDTO).build();
    this.dietFood = new ArrayList<>();
    this.nutritional = null;
  }

  // 식단 음식 추가
  public void addFood(List<FoodEntity> foodEntityList) {
    if(!CollectionUtils.isEmpty(foodEntityList)) {
      this.dietFood.addAll(foodEntityList.stream().map(food -> DietFoodEntity.builder()
          .diet(this)
          .food(food)
          .build()).toList());
    }
  }

  // 영양 성분 추가
  public void addNutritional(NutritionDTO nutritionDTO) {
    this.nutritional = NutritionalEntity.createNutritionalBuilder().nutritionDTO(nutritionDTO).diet(this).build();
  }

}
