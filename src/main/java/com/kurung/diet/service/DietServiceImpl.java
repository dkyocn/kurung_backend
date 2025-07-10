package com.kurung.diet.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.diet.dto.DietDTO;
import com.kurung.diet.dto.DietScoreDTO;
import com.kurung.diet.dto.FoodDTO;
import com.kurung.diet.entity.DietEntity;
import com.kurung.diet.entity.DietScoreEntity;
import com.kurung.diet.entity.FoodEntity;
import com.kurung.diet.repository.DietRepository;
import com.kurung.diet.repository.DietScoreRepository;
import com.kurung.diet.repository.FoodRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DietServiceImpl implements DietService {

  private final DietRepository dietRepository;
  private final FoodRepository foodRepository;
  private final DietScoreRepository dietScoreRepository;


  @Override
  public DietDTO getDietById(int id) {

    DietEntity dietById = dietRepository.getDietById(id);

    if (dietById == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.DIET_NOT_FOUND);
    }

    return DietDTO.toDietBuilder().dietEntity(dietById).build();
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
  public List<FoodDTO> getFoodList(String keyword) {
    List<FoodEntity> foodList = foodRepository.getFoodList(keyword);

    return foodList.stream()
        .map(foodEntity -> FoodDTO.toFoodBuilder().foodEntity(foodEntity).build()).collect(
            Collectors.toList());
  }


}
