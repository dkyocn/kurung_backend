package com.kurung.diet.repository;

import com.kurung.diet.entity.NutritionalEntity;
import com.kurung.diet.enumeration.DIETTYPE;

public interface NutritionalRepositorySupport {
  NutritionalEntity getNutritionalById(int id, DIETTYPE type);
}
