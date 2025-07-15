package com.kurung.diet.repository;

import com.kurung.diet.entity.FoodEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodRepositorySupport {

  FoodEntity getFoodById(int id);
  List<FoodEntity> getFoodList(String keyword);
  Page<FoodEntity> getFoodByPage(String keyword, Pageable pageable);
}
