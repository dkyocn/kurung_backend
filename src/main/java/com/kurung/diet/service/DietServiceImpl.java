package com.kurung.diet.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.diet.dto.DietDTO;
import com.kurung.diet.dto.DietScoreDTO;
import com.kurung.diet.dto.FoodDTO;
import com.kurung.diet.dto.NutritionDTO;
import com.kurung.diet.entity.DietEntity;
import com.kurung.diet.entity.DietEntity.createDietBuilder;
import com.kurung.diet.entity.DietScoreEntity;
import com.kurung.diet.entity.FoodEntity;
import com.kurung.diet.enumeration.DIETTYPE;
import com.kurung.diet.repository.DietRepository;
import com.kurung.diet.repository.DietScoreRepository;
import com.kurung.diet.repository.FoodRepository;
import com.kurung.diet.repository.NutritionalRepository;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class DietServiceImpl implements DietService {

  private final UserService userService;

  private final DietRepository dietRepository;
  private final FoodRepository foodRepository;
  private final DietScoreRepository dietScoreRepository;
  private final NutritionalRepository nutritionalRepository;

  // DIET

  @Override
  public DietDTO getDietById(int id) {

    DietEntity dietById = dietRepository.getDietById(id);

    if (dietById == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.DIET_NOT_FOUND);
    }

    return DietDTO.toDietBuilder().dietEntity(dietById).build();
  }


  @Override
  @Transactional
  public void createDiet(DietDTO dietDTO) {

    List<FoodEntity> foodEntityList = new ArrayList<>();

    int sodium = 0;
    int crab = 0;
    int sugar = 0;
    int transFat = 0;
    int saturatedFat = 0;
    int cholesterol = 0;
    int protein = 0;
    int kcal = 0;

    UserDTO userByUuid = userService.getUserByUuid(dietDTO.getUser().getUserUuid());

    // 식단 저장
    DietEntity dietEntity = dietRepository.save(DietEntity.createDietBuilder()
        .dietDTO(dietDTO)
        .userDTO(userByUuid)
        .build());

    if (!CollectionUtils.isEmpty(dietDTO.getFoodList())) {
      // foodId 목록 추출
      List<Integer> foodIds = dietDTO.getFoodList().stream()
          .map(FoodDTO::getFoodId)
          .collect(Collectors.toList());

      // 한 번에 조회 (findAllById 사용)
      foodEntityList = foodRepository.findAllById(foodIds);

      // 누락된 foodId 확인
      if (foodEntityList.size() != foodIds.size()) {
        throw new CustomIllegalArgumentException(CustomHttpStatus.FOOD_NOT_FOUND);
      }

      sodium = foodEntityList.stream()
          .mapToInt(food -> nutritionalRepository.getNutritionalById(food.getFoodId(), DIETTYPE.FOOD).getSodium())
          .sum();
      crab = foodEntityList.stream()
          .mapToInt(food -> nutritionalRepository.getNutritionalById(food.getFoodId(), DIETTYPE.FOOD).getCarb())
          .sum();

      sugar = foodEntityList.stream()
          .mapToInt(food -> nutritionalRepository.getNutritionalById(food.getFoodId(), DIETTYPE.FOOD).getSugar())
          .sum();

      transFat = foodEntityList.stream()
          .mapToInt(food -> nutritionalRepository.getNutritionalById(food.getFoodId(), DIETTYPE.FOOD).getTransFat())
          .sum();

      saturatedFat = foodEntityList.stream()
          .mapToInt(food -> nutritionalRepository.getNutritionalById(food.getFoodId(), DIETTYPE.FOOD).getSaturatedFat())
          .sum();

      cholesterol = foodEntityList.stream()
          .mapToInt(food -> nutritionalRepository.getNutritionalById(food.getFoodId(), DIETTYPE.FOOD).getCholesterol())
          .sum();
      protein = foodEntityList.stream()
          .mapToInt(food -> nutritionalRepository.getNutritionalById(food.getFoodId(), DIETTYPE.FOOD).getProtein())
          .sum();
      kcal = foodEntityList.stream()
          .mapToInt(food -> nutritionalRepository.getNutritionalById(food.getFoodId(), DIETTYPE.FOOD).getKcal())
          .sum();

    }

    // 포함된 음식 영양성분 합
    NutritionDTO nutritionDTO = NutritionDTO.builder().sodium(sodium).carb(crab).sugar(sugar)
        .transFat(transFat)
        .saturatedFat(saturatedFat).cholesterol(cholesterol).protein(protein).kcal(kcal).build();

    // 음식 추가
    dietEntity.addFood(foodEntityList);
    // 영양 성분
    dietEntity.addNutritional(nutritionDTO);
  }

  @Override
  public DietScoreDTO getDietScoreById(int id) {

    DietScoreEntity dietScoreEntity = dietScoreRepository.getDietScoreById(id);

    if (dietScoreEntity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.SCORE_NOT_FOUND);
    }
    return DietScoreDTO.toDietScoreBuilder().dietScoreEntity(dietScoreEntity).build();
  }

  // FOOD

  @Override
  public FoodDTO getFoodById(int id) {

    FoodEntity foodById = foodRepository.getFoodById(id);

    if (foodById == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.FOOD_NOT_FOUND);
    }

    return FoodDTO.toFoodBuilder().foodEntity(foodById).build();
  }

  @Override
  public List<FoodDTO> getFoodList(String keyword) {
    List<FoodEntity> foodList = foodRepository.getFoodList(keyword);

    return foodList.stream()
        .map(foodEntity -> FoodDTO.toFoodBuilder().foodEntity(foodEntity).build()).collect(
            Collectors.toList());
  }


}
