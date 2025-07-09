package com.kurung.diet.repository;

import com.kurung.diet.entity.DietEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietScoreRepository extends JpaRepository<DietEntity, Integer>,
    DietScoreRepositorySupport {

}
