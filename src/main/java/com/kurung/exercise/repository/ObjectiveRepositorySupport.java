package com.kurung.exercise.repository;

import com.kurung.exercise.entity.ObjectiveEntity;
import java.time.LocalDateTime;

public interface ObjectiveRepositorySupport {

  ObjectiveEntity getObjectiveByMonth(LocalDateTime startDate, LocalDateTime endDate,
      String userUuid);

  ObjectiveEntity findByObjectiveId(int objectiveId);
}
