package com.kurung.diet.service;

import com.kurung.diet.dto.DietDTO;
import com.kurung.diet.dto.DietScoreDTO;
import com.kurung.diet.dto.FoodDTO;
import com.kurung.diet.enumeration.MEAL;
import java.time.LocalDateTime;
import java.util.List;

public interface DietService {

    // 식단 조회
    DietDTO getCurrentDiet(LocalDateTime currentDate, String userUuid, MEAL meal);
    // 식단 점수 조회
    DietScoreDTO getDietScoreById(int id);
    List<DietScoreDTO> getDietScoreMonthList(LocalDateTime currentDate);
    // 식단 저장
    void createDiet(DietDTO dietDTO);
    // 식단 수정
    void updateDiet(DietDTO dietDTO);
    // 식단 삭제
    void deleteDietById(int id);

    // 음식 조회
    FoodDTO getFoodById(int id);
    // 음식 리스트 조회
    List<FoodDTO> getFoodList(String keyword);

}
