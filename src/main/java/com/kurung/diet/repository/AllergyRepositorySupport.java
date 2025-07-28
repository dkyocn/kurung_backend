package com.kurung.diet.repository;

import com.kurung.diet.entity.AllergyEntity;
import java.util.List;

public interface AllergyRepositorySupport {

  List<AllergyEntity> getAllergyList(String keyword);

}
