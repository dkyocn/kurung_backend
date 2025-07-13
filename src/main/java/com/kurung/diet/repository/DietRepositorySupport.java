package com.kurung.diet.repository;


import com.kurung.diet.entity.DietEntity;
import com.kurung.diet.enumeration.MEAL;
import java.time.LocalDateTime;

public interface DietRepositorySupport {

    DietEntity getDietById(int id);
    DietEntity getCurrentDiet(LocalDateTime currentDate, String userUuid, MEAL meal);
}
