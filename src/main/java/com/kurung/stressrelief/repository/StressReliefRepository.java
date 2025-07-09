package com.kurung.stressrelief.repository;

import com.kurung.stressrelief.entity.StressReliefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StressReliefRepository extends JpaRepository<StressReliefEntity, Integer>, StressReliefRepositorySupport {
}





