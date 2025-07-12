package com.kurung.diagnosis.repository;

import com.kurung.diagnosis.entity.RecommendedGoalEntity;
import java.util.List;

public interface GoalRepositorySupport {

  List<RecommendedGoalEntity> getGoalById(int id);
}
