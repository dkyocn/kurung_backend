package com.kurung.diet.service;

import com.kurung.diet.dto.DietDTO;
import com.kurung.diet.dto.DietScoreDTO;
import com.kurung.diet.dto.FoodDTO;
import java.util.List;

public interface DietService {

    DietDTO getDietById(int id);
    DietScoreDTO getDietScoreById(int id);

    // 음식 조회
    FoodDTO getFoodById(int id);
    // 음식 리스트 조회
    List<FoodDTO> getFoodList(String keyword);
}
