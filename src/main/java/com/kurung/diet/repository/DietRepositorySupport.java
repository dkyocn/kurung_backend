package com.kurung.diet.repository;


import com.kurung.diet.entity.DietEntity;
import com.kurung.diet.enumeration.MEAL;
import java.time.LocalDateTime;
import java.util.List;

public interface DietRepositorySupport {

    DietEntity getDietById(int id);
    DietEntity getCurrentDiet(LocalDateTime startTime, LocalDateTime endTime, String userUuid, MEAL meal);
    List<DietEntity> getTodayDiet(LocalDateTime startTime, LocalDateTime endTime, String userUuid);
}
