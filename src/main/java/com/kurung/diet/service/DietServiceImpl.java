package com.kurung.diet.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.common.exception.CustomRunTimeException;
import com.kurung.common.security.service.SessionService;
import com.kurung.diet.dto.DietDTO;
import com.kurung.diet.dto.DietScoreDTO;
import com.kurung.diet.dto.FoodDTO;
import com.kurung.diet.dto.NutritionDTO;
import com.kurung.diet.entity.DietEntity;
import com.kurung.diet.entity.DietScoreEntity;
import com.kurung.diet.entity.FoodEntity;
import com.kurung.diet.enumeration.DIETTYPE;
import com.kurung.diet.enumeration.MEAL;
import com.kurung.diet.repository.DietRepository;
import com.kurung.diet.repository.DietScoreRepository;
import com.kurung.diet.repository.FoodRepository;
import com.kurung.diet.repository.NutritionalRepository;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.service.UserService;
import java.time.LocalDateTime;
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
  private final SessionService sessionService;
  private final DietRepository dietRepository;
  private final FoodRepository foodRepository;
  private final DietScoreRepository dietScoreRepository;
  private final NutritionalRepository nutritionalRepository;

  // DIET

  @Override
  public DietDTO getCurrentDiet(LocalDateTime currentDate, String userUuid,  MEAL meal) {

    DietEntity dietById = dietRepository.getCurrentDiet(currentDate,userUuid, meal);

    if (dietById != null) {
      return DietDTO.toDietBuilder().dietEntity(dietById).build();
    } else {
      return null;
    }
  }


  @Override
  @Transactional
  public void createDiet(DietDTO dietDTO) {

    UserDTO userByUuid = userService.getUserByUuid(dietDTO.getUser().getUserUuid());

    try {
      // 식단 저장
      DietEntity dietEntity = dietRepository.save(DietEntity.createDietBuilder()
          .dietDTO(dietDTO)
          .userDTO(userByUuid)
          .build());

      if (!CollectionUtils.isEmpty(dietDTO.getFoodList())) {
        // 음식 저장
        List<FoodEntity> foodEntityList = dietFoodSetting(dietDTO);
        // 음식 추가
        dietEntity.addFood(foodEntityList);

        NutritionDTO nutritionDTO = dietNutritionSetting(foodEntityList);
        // 영양 성분
        dietEntity.addNutritional(nutritionDTO);
      }

    } catch (Exception ex) {
      throw new CustomRunTimeException(CustomHttpStatus.DIET_SAVE_ERROR);
    }

  }

  @Override
  @Transactional
  public void updateDiet(DietDTO dietDTO) {
    List<FoodEntity> foodEntityList = new ArrayList<>();

    // 수정할 식단 조회
    DietEntity dietById = dietRepository.getDietById(dietDTO.getDietId());

    if (dietById == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.DIET_NOT_FOUND);
    }

    try {
      if (!CollectionUtils.isEmpty(dietDTO.getFoodList())) {
        dietById.getDietFood().clear();
        // 음식 설정
        foodEntityList = dietFoodSetting(dietDTO);
        // 음식 추가
        dietById.addFood(foodEntityList);
      }

      if (dietDTO.getNutrition() != null) {
        NutritionDTO nutritionDTO = dietNutritionSetting(foodEntityList);
        // 영양 성분
        dietById.getNutritional().updateNutritional(nutritionDTO);
      }
    } catch (Exception ex) {
      throw new CustomRunTimeException(CustomHttpStatus.DIET_UPDATE_ERROR);
    }

  }

  @Override
  public void deleteDietById(int id) {

    // 삭제할 식단 조회
    DietEntity dietById = dietRepository.getDietById(id);

    if (dietById == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.DIET_NOT_FOUND);
    }

    try {
      dietRepository.delete(dietById);
    } catch (Exception ex) {
      throw new CustomRunTimeException(CustomHttpStatus.DIET_DELETE_ERROR);
    }
  }

  // 식단 음식 설정
  private List<FoodEntity> dietFoodSetting(DietDTO dietDTO) {

    List<FoodEntity> foodEntityList;

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

    return foodEntityList;
  }

  // 식단 영양성분 설정
  private NutritionDTO dietNutritionSetting(List<FoodEntity> foodEntityList) {

    int sodium = 0;
    int crab = 0;
    int sugar = 0;
    int transFat = 0;
    int saturatedFat = 0;
    int cholesterol = 0;
    int protein = 0;
    int kcal = 0;

    sodium = foodEntityList.stream()
        .mapToInt(food -> nutritionalRepository.getNutritionalById(food.getFoodId(), DIETTYPE.FOOD)
            .getSodium())
        .sum();
    crab = foodEntityList.stream()
        .mapToInt(food -> nutritionalRepository.getNutritionalById(food.getFoodId(), DIETTYPE.FOOD)
            .getCarb())
        .sum();

    sugar = foodEntityList.stream()
        .mapToInt(food -> nutritionalRepository.getNutritionalById(food.getFoodId(), DIETTYPE.FOOD)
            .getSugar())
        .sum();

    transFat = foodEntityList.stream()
        .mapToInt(food -> nutritionalRepository.getNutritionalById(food.getFoodId(), DIETTYPE.FOOD)
            .getTransFat())
        .sum();

    saturatedFat = foodEntityList.stream()
        .mapToInt(food -> nutritionalRepository.getNutritionalById(food.getFoodId(), DIETTYPE.FOOD)
            .getSaturatedFat())
        .sum();

    cholesterol = foodEntityList.stream()
        .mapToInt(food -> nutritionalRepository.getNutritionalById(food.getFoodId(), DIETTYPE.FOOD)
            .getCholesterol())
        .sum();
    protein = foodEntityList.stream()
        .mapToInt(food -> nutritionalRepository.getNutritionalById(food.getFoodId(), DIETTYPE.FOOD)
            .getProtein())
        .sum();
    kcal = foodEntityList.stream()
        .mapToInt(food -> nutritionalRepository.getNutritionalById(food.getFoodId(), DIETTYPE.FOOD)
            .getKcal())
        .sum();

    // 포함된 음식 영양성분 합
    return NutritionDTO.builder().sodium(sodium).carb(crab).sugar(sugar)
        .transFat(transFat)
        .saturatedFat(saturatedFat).cholesterol(cholesterol).protein(protein).kcal(kcal).build();
  }

  @Override
  public DietScoreDTO getDietScoreById(int id) {

    DietScoreEntity dietScoreEntity = dietScoreRepository.getDietScoreById(id);

    if (dietScoreEntity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.SCORE_NOT_FOUND);
    }
    return DietScoreDTO.toDietScoreBuilder().dietScoreEntity(dietScoreEntity).build();
  }

  @Override
  public List<DietScoreDTO> getDietScoreMonthList(LocalDateTime currentDate) {
    UserDTO userDTO = sessionService.getUserFromToken();

    List<DietScoreEntity> dietScoreMonthList = dietScoreRepository.getDietScoreMonthList(
        currentDate.toLocalDate().withDayOfMonth(1).atStartOfDay(),
        currentDate.toLocalDate().withDayOfMonth(currentDate.toLocalDate().lengthOfMonth()).atStartOfDay(), userDTO.getUserUuid());

    return dietScoreMonthList.stream().map(
        dietScoreEntity -> DietScoreDTO.toDietScoreBuilder().dietScoreEntity(dietScoreEntity)
            .build()).collect(Collectors.toList());
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
