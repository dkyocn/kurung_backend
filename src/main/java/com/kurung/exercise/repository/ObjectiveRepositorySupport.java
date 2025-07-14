package com.kurung.exercise.repository;

import com.kurung.exercise.entity.ObjectiveEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface ObjectiveRepositorySupport {

  List<ObjectiveEntity> getObjectiveMonthList(LocalDateTime startDate, LocalDateTime endDate,
      String userUuid);

}
