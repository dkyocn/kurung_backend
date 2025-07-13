package com.kurung.diet.repository;

import com.kurung.diet.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Integer>, FoodRepositorySupport {

}
