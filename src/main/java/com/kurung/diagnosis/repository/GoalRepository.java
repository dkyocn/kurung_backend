package com.kurung.diagnosis.repository;

import com.kurung.diagnosis.entity.RecommendedGoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<RecommendedGoalEntity, Integer>, GoalRepositorySupport {
}
