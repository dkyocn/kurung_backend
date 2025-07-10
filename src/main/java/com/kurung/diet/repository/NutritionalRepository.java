package com.kurung.diet.repository;

import com.kurung.diet.entity.NutritionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionalRepository extends JpaRepository<NutritionalEntity, Integer>, NutritionalRepositorySupport {
}
