package com.kurung.diet.repository;

import com.kurung.diet.entity.DietScoreEntity;

public interface DietScoreRepositorySupport {


  DietScoreEntity getDietScoreById(int foodId);
}
