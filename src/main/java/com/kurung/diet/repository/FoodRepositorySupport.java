package com.kurung.diet.repository;

import com.kurung.diet.entity.FoodEntity;
import java.util.List;

public interface FoodRepositorySupport {

  FoodEntity getFoodById(int id);
  List<FoodEntity> getFoodList(String keyword);

}
