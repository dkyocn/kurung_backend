package com.kurung.diet.service;

import com.kurung.diet.dto.DietDTO;
import com.kurung.diet.dto.DietScoreDTO;
import com.kurung.diet.dto.FoodDTO;
import java.util.List;

public interface DietService {

    // 식단 조회
    DietDTO getDietById(int id);
    // 식단 점수 조회
    DietScoreDTO getDietScoreById(int id);
    // 식단 저장
    void createDiet(DietDTO dietDTO);
    // 식단 수정
    void updateDiet(DietDTO dietDTO);

    // 음식 조회
    FoodDTO getFoodById(int id);
    // 음식 리스트 조회
    List<FoodDTO> getFoodList(String keyword);

}
